package org.geektimes.projects.user.validator.bean.validation;

import org.geektimes.projects.user.domain.User;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserValidAnnotationValidator implements ConstraintValidator<UserValid, User> {

    private int idRange;
    private int passwordMinLength;
    private int passwordMaxLength;
    private String phoneRegex;


    public void initialize(UserValid annotation) {
        this.idRange = annotation.idRange();
        this.passwordMinLength = annotation.passwordMinLength();
        this.passwordMaxLength = annotation.passwordMaxLength();
        this.phoneRegex = annotation.phoneRegex();
    }

    @Override
    public boolean isValid(User value, ConstraintValidatorContext context) {

        // 获取模板信息
        // String s = context.getDefaultConstraintMessageTemplate();

        if (value.getId() != null && value.getId().compareTo((long) this.idRange) < 0) {
            return false;
        }
        else if (value.getPassword().length() > this.passwordMaxLength || value.getPassword().length() < this.passwordMinLength) {
            return false;
        }
        else if (!value.getPhoneNumber().matches(this.phoneRegex)) {
            return false;
        }


        return true;
    }
}
