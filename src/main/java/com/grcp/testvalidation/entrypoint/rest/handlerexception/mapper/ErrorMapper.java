package com.grcp.testvalidation.entrypoint.rest.handlerexception.mapper;

import com.grcp.testvalidation.entrypoint.rest.handlerexception.json.Error;
import com.grcp.testvalidation.entrypoint.rest.handlerexception.json.ErrorResponse;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component
public class ErrorMapper {

    public ErrorResponse mapToErrorResponse(BindingResult bindingResult) {
        List<Error> errors = bindingResult.getFieldErrors().stream()
                .map(fieldError -> buildError(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());

        return mapToErrorResponse(errors);
    }

    public ErrorResponse mapToErrorResponse(Set<ConstraintViolation<?>> constraintViolations) {
        List<Error> errors = constraintViolations.stream()
                .map(constraint -> buildError(constraint.getPropertyPath().toString(), constraint.getMessage()))
                .collect(Collectors.toList());
        return ErrorResponse.builder().errors(errors).build();
    }

    private ErrorResponse mapToErrorResponse(List<Error> errors) {
        return ErrorResponse.builder()
                .errors(errors)
                .build();
    }

    private Error buildError(String field, String errorMessage) {
        return Error.builder()
                .field(field)
                .errorMessage(errorMessage)
                .build();
    }
}
