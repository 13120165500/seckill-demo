package com.mall.seckill.mapper;

import com.mall.seckill.entity.SeckillGood;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yangzhiqing
 * @since 2021-10-14
 */
public interface SeckillGoodMapper extends BaseMapper<SeckillGood> {

    void decreaseStockCount(Long goodId);
}
