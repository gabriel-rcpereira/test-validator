package com.grcp.testvalidation.product.entrypoint.rest;

import com.grcp.testvalidation.product.entrypoint.rest.json.person.PersonRequest;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
public class PersonController {

    private final Validator validator;

    public PersonController(Validator validator) {
        this.validator = validator;
    }

    @PostMapping("/api/v1/persons")
    public ResponseEntity<Void> createPerson(@RequestBody PersonRequest personRequest) {
        Set<ConstraintViolation<PersonRequest>> validate = validator.validate(personRequest);
        if (!validate.isEmpty()) {
            throw new ConstraintViolationException("createPersonError", validate);
        }
        return ResponseEntity.status(CREATED).build();
    }
}
