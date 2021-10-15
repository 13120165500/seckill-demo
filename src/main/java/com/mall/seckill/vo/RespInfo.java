package com.mall.seckill.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RespInfo {

    SUCCESS(200, "success"),
    ERROR(500, "server intern error"),
    //登录错误信息5002模块
    LOGIN_ERROR(500210, "手机号或密码错误"),
    LOGIN_VALID_ERROR(500211, "手机号或密码校验出错"),
    //秒杀错误信息5003模块
    EMPTY_STOCK_ERROR(500310, "商品已售罄"),
    REPEAT_BUY_ERROR(500311, "该秒杀商品每人限购一件");

    private int code;
    private String message;

}
