package com.mall.seckill.vo;

import com.mall.seckill.validator.annotation.IsMobile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginVo {
    @IsMobile
    @NotBlank
    String mobile;

    @NotBlank
    String password;

}
