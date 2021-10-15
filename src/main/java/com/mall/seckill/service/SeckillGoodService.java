package com.mall.seckill.service;

import com.mall.seckill.entity.SeckillGood;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yangzhiqing
 * @since 2021-10-14
 */
public interface SeckillGoodService extends IService<SeckillGood> {
    void decreaseStockCount(Long goodId);
}
