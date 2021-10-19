package com.mall.seckill.service.impl;

import com.mall.seckill.entity.User;
import com.mall.seckill.exception.GlobalException;
import com.mall.seckill.mapper.UserMapper;
import com.mall.seckill.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.seckill.utils.CookieUtil;
import com.mall.seckill.utils.MD5Util;
import com.mall.seckill.utils.UUIDUtil;
import com.mall.seckill.vo.LoginVo;
import com.mall.seckill.vo.RespBean;
import com.mall.seckill.vo.RespInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.Duration;
import java.util.UUID;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yangzhiqing
 * @since 2021-10-11
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    UserMapper userMapper;

    @Resource
    RedisTemplate<String, Object> redisTemplate;

    @Override
    public RespBean doLogin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        //从缓存中取该用户信息
        String userTicket = (String) redisTemplate.opsForValue().get("user:" + loginVo.getMobile());
        User user = null;
        //如果该用户缓存中取到的值为空,说明缓存中没有该用户的信息
        if(StringUtils.isBlank(userTicket)) {
            user = userMapper.selectById(loginVo.getMobile());
            if(user == null) {
                redisTemplate.opsForValue().set("user:" + loginVo.getMobile(), "null", Duration.ofMinutes(30));
                throw new GlobalException(new RespBean(RespInfo.LOGIN_ERROR.getCode(), RespInfo.LOGIN_ERROR.getMessage(), null));
            }
            userTicket = UUIDUtil.uuid();
            redisTemplate.opsForValue().set("user:" + loginVo.getMobile(), userTicket);
            redisTemplate.opsForValue().set("user:" + userTicket, user);
        }
        //如果该用户缓存中取到的值为字符串null,说明该用户名对应的用户非法
        else if("null".equals(userTicket)) {
            throw new GlobalException(new RespBean(RespInfo.LOGIN_ERROR.getCode(), RespInfo.LOGIN_ERROR.getMessage(), null));
        }
        //如果该用户缓存中取到的值不为空,说明缓存了该用户的信息
        else {
            user = (User) redisTemplate.opsForValue().get("user:" + userTicket);
        }
        if(!user.getPassword().equals(MD5Util.recvPassToDbPass(loginVo.getPassword(), "1a2b3c4d"))) {
            throw new GlobalException(new RespBean(RespInfo.LOGIN_ERROR.getCode(), RespInfo.LOGIN_ERROR.getMessage(), null));
        }
        CookieUtil.setCookie(response, "userTicket", userTicket);
        return RespBean.success(userTicket);
    }

    @Override
    public Object getUserByCookie(String ticket) {
        if (StringUtils.isEmpty(ticket)) {
            return null;
        }
        User user = (User) redisTemplate.opsForValue().get("user:" + ticket);
        return user;
    }
}
