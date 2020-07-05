package com.grcp.testvalidation.product.entrypoint.rest.json.customvalidator;

import com.grcp.testvalidation.product.entrypoint.rest.json.ProductAttributeRequest;
import java.util.List;
import java.util.Optional;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ProductAttributeValidator implements ConstraintValidator<ProductAttribute, List<ProductAttributeRequest>> {

    private static final int MIN_SIZE = 1;

    @Override
    public boolean isValid(List<ProductAttributeRequest> values, ConstraintValidatorContext context) {
        if (Optional.ofNullable(values).isEmpty()) {
            return false;
        }

        return values.size() > MIN_SIZE;
    }
}
