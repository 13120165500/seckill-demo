package com.mall.seckill.exception;

import com.mall.seckill.vo.RespBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GlobalException extends RuntimeException{

    RespBean respBean;

}
