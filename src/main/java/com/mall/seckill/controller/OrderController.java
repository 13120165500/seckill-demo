package com.mall.seckill.controller;


import com.mall.seckill.entity.Order;
import com.mall.seckill.service.GoodService;
import com.mall.seckill.service.OrderService;
import com.mall.seckill.vo.GoodDetailVo;
import com.mall.seckill.vo.RespBean;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
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
@RequestMapping("//order")
public class OrderController {

    @Resource
    OrderService orderService;

    @Resource
    GoodService goodService;

    @RequestMapping("/detail")
    @ResponseBody
    public RespBean orderDetail(Long orderId){
        Order order = orderService.getById(orderId);
        GoodDetailVo good = goodService.getGoodDetailByGoodId(order.getGoodId());
        Map<String, Object> data = new HashMap<>();
        data.put("order", order);
        data.put("good", good);
        return RespBean.success(data);
    }

}
