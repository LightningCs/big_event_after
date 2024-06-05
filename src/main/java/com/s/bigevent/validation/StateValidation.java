package com.s.bigevent.validation;

import com.s.bigevent.anno.State;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

//自定义校验数据类
public class StateValidation implements ConstraintValidator<State, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null) return false;
        if (s.equals("草稿") || s.equals("已发布")) return true;
        return false;
    }
}
