package com.mall.seckill.service.impl;

import com.mall.seckill.entity.SeckillGood;
import com.mall.seckill.mapper.SeckillGoodMapper;
import com.mall.seckill.service.SeckillGoodService;
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
public class SeckillGoodServiceImpl extends ServiceImpl<SeckillGoodMapper, SeckillGood> implements SeckillGoodService {

    @Resource
    SeckillGoodMapper seckillGoodMapper;

    @Override
    public void decreaseStockCount(Long goodId) {
        seckillGoodMapper.decreaseStockCount(goodId);
    }
}
