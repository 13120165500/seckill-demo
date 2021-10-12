package com.mall.seckill.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RespInfo {

    SUCCESS(200, "success"),
    ERROR(500, "server intern error"),
    LOGIN_ERROR(500210, "手机号或密码错误"),
    LOGIN_VALID_ERROR(500211, "手机号或密码校验出错");

    private int code;
    private String message;

}
