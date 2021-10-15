package com.mall.seckill.service;

import com.mall.seckill.entity.SeckillOrder;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yangzhiqing
 * @since 2021-10-14
 */
public interface SeckillOrderService extends IService<SeckillOrder> {

    SeckillOrder createSeckillOrder(Long goodId, Long userId, Long orderId);

}
