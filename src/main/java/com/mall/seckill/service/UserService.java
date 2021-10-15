package com.mall.seckill.service;

import com.mall.seckill.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.seckill.vo.LoginVo;
import com.mall.seckill.vo.RespBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yangzhiqing
 * @since 2021-10-11
 */
public interface UserService extends IService<User> {

    RespBean doLogin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response, HttpSession session);

    Object getUserByCookie(String ticket);
}
