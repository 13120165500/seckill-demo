package com.mall.seckill.service.impl;

import com.mall.seckill.entity.Good;
import com.mall.seckill.mapper.GoodMapper;
import com.mall.seckill.service.GoodService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.seckill.vo.GoodDetailVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yangzhiqing
 * @since 2021-10-14
 */
@Service
public class GoodServiceImpl extends ServiceImpl<GoodMapper, Good> implements GoodService {

    @Resource
    GoodMapper goodMapper;

    @Override
    public List<GoodDetailVo> getGoodDetailList() {
        return goodMapper.getGoodDetailList();
    }

    @Override
    public GoodDetailVo getGoodDetailByGoodId(Long goodId) {
        return goodMapper.getGoodDetailByGoodId(goodId);
    }
}
