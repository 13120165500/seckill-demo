package com.mall.seckill.service;

import com.mall.seckill.entity.Good;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.seckill.vo.GoodDetailVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yangzhiqing
 * @since 2021-10-14
 */
public interface GoodService extends IService<Good> {

    List<GoodDetailVo> getGoodDetailList();

    GoodDetailVo getGoodDetailByGoodId(Long goodId);
}
