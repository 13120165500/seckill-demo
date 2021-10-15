package com.mall.seckill.mapper;

import com.mall.seckill.entity.Good;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mall.seckill.vo.GoodDetailVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yangzhiqing
 * @since 2021-10-14
 */
public interface GoodMapper extends BaseMapper<Good> {

    List<GoodDetailVo> getGoodDetailList();

    GoodDetailVo getGoodDetailByGoodId(Long goodId);
}
