package com.mall.seckill.exception.handler;

import com.mall.seckill.exception.GlobalException;
import com.mall.seckill.vo.RespBean;
import com.mall.seckill.vo.RespInfo;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({Exception.class})
    public RespBean handle(Exception exception) {
        //处理器内自定义逻辑异常
        if(exception instanceof GlobalException) {
            GlobalException e = (GlobalException) exception;
            return e.getRespBean();
        }
        //@Valid参数校验异常
        else if(exception instanceof BindException) {
            BindException e = (BindException) exception;
            String defaultMessage = e.getAllErrors().get(0).getDefaultMessage();
            return new RespBean(RespInfo.LOGIN_VALID_ERROR.getCode(), RespInfo.LOGIN_VALID_ERROR.getMessage() + ":" + defaultMessage, null);
        }
        exception.printStackTrace();
        return RespBean.error(RespInfo.ERROR);
    }

}
