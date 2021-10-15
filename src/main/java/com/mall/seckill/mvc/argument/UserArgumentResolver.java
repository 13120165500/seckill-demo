package com.mall.seckill.mvc.argument;

import com.mall.seckill.entity.User;
import com.mall.seckill.exception.UserNotExistException;
import com.mall.seckill.utils.CookieUtil;
import org.springframework.core.MethodParameter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Component
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

    @Resource
    RedisTemplate<String, Object> redisTemplate;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(User.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        String userTicket = CookieUtil.getCookieValue(request, "userTicket");
        if(userTicket != null) {
            User user = (User) redisTemplate.opsForValue().get("user:" + userTicket);
            if(user != null) {
                return user;
            }
        }
        return null;
    }
}
