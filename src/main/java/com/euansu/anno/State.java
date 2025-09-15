package com.euansu.anno;

import com.euansu.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented // 元注解，标识State是可以抽取到帮助文档
@Target({ElementType.FIELD}) // 元注解，标识State是可以作用到字段上
@Retention(RetentionPolicy.RUNTIME) // 元注解，标识State在那个阶段会被保留
@Constraint(validatedBy = {StateValidation.class}) // 指定谁来给State提供校验规则

public @interface State {
    // 提供校验失败后的提供信息
    String message() default "state参数的值只能是已发布或者草稿";
    // 指定分组
    Class<?>[] groups() default {};
    // 负载，可以获取State注解的附加信息，一般不用
    Class<? extends Payload>[] payload() default {};

}
