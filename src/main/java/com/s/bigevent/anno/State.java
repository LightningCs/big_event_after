package com.s.bigevent.anno;

import com.s.bigevent.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

//自定义注解
@Documented
@Constraint(
        validatedBy = {StateValidation.class}
)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface State {
    String message() default "state参数只能是草稿或者已发布";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
