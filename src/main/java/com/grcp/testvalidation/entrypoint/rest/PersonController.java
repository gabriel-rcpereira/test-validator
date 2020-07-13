package com.grcp.testvalidation.entrypoint.rest;

import com.grcp.testvalidation.entrypoint.rest.json.person.DependentRequest;
import com.grcp.testvalidation.entrypoint.rest.json.person.PersonRequest;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
public class PersonController {

    private final Validator validator;
    private final org.springframework.validation.Validator dependentProgrammaticallyValidator;

    public PersonController(Validator validator,
                            @Qualifier("dependentProgrammaticallyValidator")
                                    org.springframework.validation.Validator dependentProgrammaticallyValidator) {
        this.validator = validator;
        this.dependentProgrammaticallyValidator = dependentProgrammaticallyValidator;
    }

    @PostMapping("/api/v1/persons")
    public ResponseEntity<Void> createPerson(@RequestBody PersonRequest personRequest) {
        validatePersonProgrammatically(personRequest);
        return ResponseEntity.status(CREATED).build();
    }

    @PostMapping("/api/v1/persons/dependents")
    public ResponseEntity<Void> createDependent(@RequestBody DependentRequest dependentRequest) throws BindException {
        validateDependentRequestCustomProgrammatically(dependentRequest);
        return ResponseEntity.status(CREATED).build();
    }

    private void validatePersonProgrammatically(PersonRequest personRequest) {
        Set<ConstraintViolation<PersonRequest>> validate = validator.validate(personRequest);
        if (!validate.isEmpty()) {
            throw new ConstraintViolationException("createPersonError", validate);
        }
    }

    private void validateDependentRequestCustomProgrammatically(DependentRequest dependentRequest) throws BindException {
        BindingResult bindingResult = new BeanPropertyBindingResult(dependentRequest, "dependent");
        ValidationUtils.invokeValidator(dependentProgrammaticallyValidator, dependentRequest, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }
    }
}
