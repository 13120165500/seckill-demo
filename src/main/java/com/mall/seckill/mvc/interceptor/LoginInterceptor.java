package com.mall.seckill.mvc.interceptor;

import com.mall.seckill.entity.User;
import com.mall.seckill.utils.CookieUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Resource
    RedisTemplate<String, Object> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!request.getRequestURI().startsWith("/login")) {
            String userTicket = CookieUtil.getCookieValue(request,"userTicket");
            User user = (User) redisTemplate.opsForValue().get("user:" + userTicket);
            if (user != null) {
                return true;
            }
            response.sendRedirect("/login/toLogin");
            return false;
        }
        return true;
    }
}
