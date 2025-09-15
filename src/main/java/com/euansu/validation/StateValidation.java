package com.euansu.validation;

import com.euansu.anno.State;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

// <给那个注解提供校验规则，校验的数据类型>
public class StateValidation implements ConstraintValidator<State, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        // 提供校验规则
        /**
         * @parma   value 将来要校验的数据
         * @parma   constraintValidatorContext
         * @return  如果返回false，校验不通过
         *
         */
        if (s == null) {
            return false;
        }
        if(s.equals("已发布")||s.equals("草稿")){
            return true;
        }

        return false;
    }
}
