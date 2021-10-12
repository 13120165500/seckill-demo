package com.mall.seckill.validator.annotation;

import com.mall.seckill.validator.IsMobileValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.NotNull;
import java.lang.annotation.*;

@Documented
@Constraint(
        validatedBy = {IsMobileValidator.class}
)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface IsMobile {

    boolean required() default true;

    String message() default "手机号或密码格式错误！";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
