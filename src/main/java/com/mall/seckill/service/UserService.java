package com.mall.seckill.service;

import com.mall.seckill.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.seckill.vo.LoginVo;
import com.mall.seckill.vo.RespBean;

import javax.validation.Valid;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yangzhiqing
 * @since 2021-10-11
 */
public interface UserService extends IService<User> {

    RespBean doLogin(@Valid LoginVo loginVo);
}
