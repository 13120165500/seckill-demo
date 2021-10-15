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

    @Resource
    RedisTemplate<String, Object> redisTemplate;

    @Override
    public RespBean doLogin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        User user = userMapper.selectById(loginVo.getMobile());
        //查询user是否存在
        if(user == null) {
            throw new GlobalException(new RespBean(RespInfo.LOGIN_ERROR.getCode(), RespInfo.LOGIN_ERROR.getMessage(), null));
        }
        //验证user密码是否正确
        if(!MD5Util.recvPassToDbPass(loginVo.getPassword(), "1a2b3c4d").equals(user.getPassword())) {
            throw new GlobalException(new RespBean(RespInfo.LOGIN_ERROR.getCode(), RespInfo.LOGIN_ERROR.getMessage(), null));
        }
        //查询是否有旧的value存在如果有就直接返回success
        String userTicket = CookieUtil.getCookieValue(request, "userTicket");
        User oldUser = (User) redisTemplate.opsForValue().get("user:" + userTicket);
        if(oldUser == null) {
            //为用户返回Cookie,value不存Session,存入Redis中
            String ticket = UUIDUtil.uuid();
            CookieUtil.setCookie(response, "userTicket", ticket);
            redisTemplate.opsForValue().set("user:" + ticket, user);
        }
        return RespBean.success();
    }

    @Override
    public Object getUserByCookie(String ticket) {
        if(StringUtils.isEmpty(ticket)) {
            return null;
        }
        User user = (User) redisTemplate.opsForValue().get("user:" + ticket);
        return user;
    }
}
