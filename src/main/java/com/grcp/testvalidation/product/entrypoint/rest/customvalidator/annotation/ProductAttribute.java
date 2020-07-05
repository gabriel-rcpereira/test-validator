package com.grcp.testvalidation.product.entrypoint.rest.customvalidator.annotation;

import com.grcp.testvalidation.product.entrypoint.rest.customvalidator.ProductAttributeValidator;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = ProductAttributeValidator.class)
@Documented
public @interface ProductAttribute {

    String message() default "{ProductAttribute.invalid}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
