package com.mall.seckill.service.impl;

import com.mall.seckill.entity.SeckillOrder;
import com.mall.seckill.mapper.SeckillOrderMapper;
import com.mall.seckill.service.SeckillOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yangzhiqing
 * @since 2021-10-14
 */
@Service
public class SeckillOrderServiceImpl extends ServiceImpl<SeckillOrderMapper, SeckillOrder> implements SeckillOrderService {

    @Resource
    SeckillOrderMapper seckillOrderMapper;

    @Override
    public SeckillOrder createSeckillOrder(Long goodId, Long userId, Long orderId) {
        SeckillOrder seckillOrder = new SeckillOrder();
        seckillOrder.setUserId(userId);
        seckillOrder.setOrderId(orderId);
        seckillOrder.setGoodId(goodId);
        seckillOrderMapper.insert(seckillOrder);
        return seckillOrder;
    }
}
