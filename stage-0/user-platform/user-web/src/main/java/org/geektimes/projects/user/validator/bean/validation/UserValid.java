package org.geektimes.projects.user.validator.bean.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserValidAnnotationValidator.class)
public @interface UserValid {
    static String PHONE_REGEX =  "^((13[0-9])|(14[5-8])|(15([0-3]|[5-9]))|(16[6])|(17[0|4|6|7|8])|(18[0-9])|(19[8-9]))\\d{8}$";
    String message() default "";
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int idRange() default 0;
    int passwordMinLength() default 6;
    int passwordMaxLength() default 32;
    String phoneRegex() default PHONE_REGEX;
}
