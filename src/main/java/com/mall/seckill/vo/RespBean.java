package com.mall.seckill.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespBean {

    private int code;
    private String message;
    private Object obj;

    public static RespBean success() {
        return new RespBean(RespInfo.SUCCESS.getCode(), RespInfo.SUCCESS.getMessage(), null);
    }

    public static RespBean success(Object obj) {
        return new RespBean(RespInfo.SUCCESS.getCode(), RespInfo.SUCCESS.getMessage(), obj);
    }

    public static RespBean error(RespInfo respInfo) {
        return new RespBean(respInfo.getCode(), respInfo.getMessage(), null);
    }

    public static RespBean error(RespInfo respInfo, Object obj) {
        return new RespBean(respInfo.getCode(), respInfo.getMessage(), obj);
    }

}
