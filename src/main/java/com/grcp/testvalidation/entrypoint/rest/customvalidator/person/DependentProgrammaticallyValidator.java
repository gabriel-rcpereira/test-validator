package com.grcp.testvalidation.entrypoint.rest.customvalidator.person;

import com.grcp.testvalidation.entrypoint.rest.json.DependentRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component("dependentProgrammaticallyValidator")
public class DependentProgrammaticallyValidator implements Validator {

    private static final int MIN_NAME_LENGTH = 3;

    @Override
    public boolean supports(Class<?> clazz) {
        return DependentRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "field.required");
        DependentRequest request = (DependentRequest)target;
        if (!request.hasName() || request.getName().length() < MIN_NAME_LENGTH) {
            errors.rejectValue("name", "field.min-size");
        }
    }
}
