package com.grcp.testvalidation.product.entrypoint.rest.customvalidator;

import com.grcp.testvalidation.product.entrypoint.rest.json.person.PersonRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class PersonProgrammaticallyValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return PersonRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "person.name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "person.username");
        ValidationUtils.rejectIfEmpty(errors, "jobPosition", "person.jobPosition");

        PersonRequest request = (PersonRequest)target;
        if (request.getName().length() < 3) {
            errors.rejectValue("name", "person.name.min.size");
        }

        if (request.getUsername().length() < 8) {
            errors.rejectValue("name", "person.username.min.size");
        }
    }
}
