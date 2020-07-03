package com.grcp.testvalidation.product.entrypoint.rest.json.customvalidator;

import java.util.List;
import java.util.Optional;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ProductAttributeValidator implements ConstraintValidator<ProductAttribute, List> {

    private static final int MIN_SIZE = 1;

    @Override
    public boolean isValid(List value, ConstraintValidatorContext context) {
        if (Optional.ofNullable(value).isEmpty()) {
            return false;
        }

        return value.size() < MIN_SIZE;
    }
}
