package com.mall.seckill.validator;

import com.mall.seckill.utils.ValidatorUtil;
import com.mall.seckill.validator.annotation.IsMobile;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsMobileValidator implements ConstraintValidator<IsMobile, String> {

    boolean required = false;

    @Override
    public void initialize(IsMobile constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String src, ConstraintValidatorContext constraintValidatorContext) {
        if(!required) {
            if (src.isEmpty()) {
                return true;
            }
        }
        return ValidatorUtil.isMobile(src);
    }
}
