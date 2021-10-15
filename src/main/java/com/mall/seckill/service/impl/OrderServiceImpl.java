package com.mall.seckill.service.impl;

import com.mall.seckill.entity.Order;
import com.mall.seckill.entity.User;
import com.mall.seckill.mapper.OrderMapper;
import com.mall.seckill.service.GoodService;
import com.mall.seckill.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.seckill.vo.GoodDetailVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yangzhiqing
 * @since 2021-10-14
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Resource
    GoodService goodService;

    @Resource
    OrderMapper orderMapper;

    @Override
    public Order createOrder(Long goodId, User user) {
        GoodDetailVo goodDetail = goodService.getGoodDetailByGoodId(goodId);
        Order order = new Order();
        order.setUserId(user.getId());
        order.setGoodId(goodDetail.getId());
        order.setDeliveryAddrId(0L);
        order.setGoodName(goodDetail.getGoodName());
        order.setGoodCount(1);
        order.setGoodPrice(goodDetail.getSeckillPrice());
        order.setOrderChannel(1);
        order.setStatus(0);
        order.setCreateDate(new Date());
        orderMapper.insert(order);
        return order;
    }
}
