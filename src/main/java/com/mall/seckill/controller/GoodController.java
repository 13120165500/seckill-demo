package com.mall.seckill.controller;


import com.mall.seckill.entity.User;
import com.mall.seckill.service.GoodService;
import com.mall.seckill.vo.GoodDetailVo;
import com.mall.seckill.vo.RespBean;
import freemarker.template.utility.DateUtil;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.DateUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yangzhiqing
 * @since 2021-10-14
 */
@Controller
@RequestMapping("//good")
public class GoodController {

    @Resource
    GoodService goodService;

    @RequestMapping("/toList")
    public String toList(Model model, User user) {
        List<GoodDetailVo> goodDetailList = goodService.getGoodDetailList();
        model.addAttribute("goodDetailList", goodDetailList);
        model.addAttribute("user", user);
        return "good_list";
    }

    @RequestMapping("/toDetail/{goodId}")
    @ResponseBody
    public RespBean toDetail(@PathVariable Long goodId, Model model, User user) {
        GoodDetailVo goodDetailVo = goodService.getGoodDetailByGoodId(goodId);
        Date startDate = goodDetailVo.getStartDate();
        Date endDate = goodDetailVo.getEndDate();
        Date now = new Date();
        long remainSeconds = 0;
        int seckillStatus = 0; //0:秒杀未开始,1:秒杀进行中,2:秒杀已结束
        if(now.before(startDate)) {
            remainSeconds = (startDate.getTime() - now.getTime()) / 1000;
        } else if (now.after(startDate) && now.before(endDate)) {
            remainSeconds = 0;
            seckillStatus = 1;
        } else {
            remainSeconds = -1;
            seckillStatus = 2;
        }
        Map<String, Object> data = new HashMap<>();
        data.put("remainSeconds", remainSeconds);
        data.put("seckillStatus", seckillStatus);
        data.put("goodDetail", goodDetailVo);
        data.put("user", user);
        return RespBean.success(data);
    }
}
