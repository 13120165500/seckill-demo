package com.mall.seckill.service.impl;

import com.mall.seckill.entity.User;
import com.mall.seckill.exception.GlobalException;
import com.mall.seckill.mapper.UserMapper;
import com.mall.seckill.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.seckill.utils.MD5Util;
import com.mall.seckill.vo.LoginVo;
import com.mall.seckill.vo.RespBean;
import com.mall.seckill.vo.RespInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yangzhiqing
 * @since 2021-10-11
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    UserMapper userMapper;

    @Override
    public RespBean doLogin(LoginVo loginVo) {
        User user = userMapper.selectById(loginVo.getMobile());
        if(user == null) {
            throw new GlobalException(new RespBean(RespInfo.LOGIN_ERROR.getCode(), RespInfo.LOGIN_ERROR.getMessage(), null));
        }
        if(!MD5Util.recvPassToDbPass(loginVo.getPassword(), "1a2b3c4d").equals(user.getPassword())) {
            throw new GlobalException(new RespBean(RespInfo.LOGIN_ERROR.getCode(), RespInfo.LOGIN_ERROR.getMessage(), null));
        }
        return RespBean.success();
    }
}
