package com.mall.seckill.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.mall.seckill.entity.*;
import com.mall.seckill.service.GoodService;
import com.mall.seckill.service.OrderService;
import com.mall.seckill.service.SeckillGoodService;
import com.mall.seckill.service.SeckillOrderService;
import com.mall.seckill.vo.GoodDetailVo;
import com.mall.seckill.vo.RespInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
@RequestMapping("/seckill")
@Slf4j
public class SeckillController {

    @Resource
    SeckillOrderService seckillOrderService;

    @Resource
    SeckillGoodService seckillGoodService;

    @Resource
    GoodService goodService;

    @Resource
    OrderService orderService;

    @RequestMapping("/doSeckill")
    public String doSeckill(Long goodId, User user, Model model) {
        //检查用户是否登录
        if(user == null) {
            return "redirect:/login/toLogin";
        }
        //检查库存是否足够
        GoodDetailVo goodDetail = goodService.getGoodDetailByGoodId(goodId);
        if(goodDetail == null || goodDetail.getStockCount() <= 0) {
            model.addAttribute("errmsg", RespInfo.EMPTY_STOCK_ERROR.getMessage());
            return "seckill_fail";
        }
        //检查是否重复抢购
        int count = seckillOrderService.count(new QueryWrapper<SeckillOrder>().eq("good_id", goodId).eq("user_id", user.getId()));
        if (count >= 1) {
            model.addAttribute("errmsg", RespInfo.REPEAT_BUY_ERROR.getMessage());
            return "seckill_fail";
        }
        //商品数量-1
        seckillGoodService.decreaseStockCount(goodId);
        //生成订单
        Order order = orderService.createOrder(goodId, user);
        //生成秒杀订单
        SeckillOrder seckillOrder = seckillOrderService.createSeckillOrder(goodId, user.getId(), order.getId());
        //跳转页面
        model.addAttribute("goodDetail", goodDetail);
        model.addAttribute("order", order);
        return "order_detail";
    }

}
