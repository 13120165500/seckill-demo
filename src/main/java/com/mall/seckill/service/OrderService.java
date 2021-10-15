package com.mall.seckill.service;

import com.mall.seckill.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.seckill.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yangzhiqing
 * @since 2021-10-14
 */
public interface OrderService extends IService<Order> {

    Order createOrder(Long goodId, User user);
}
