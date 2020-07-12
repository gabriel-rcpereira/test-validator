package com.grcp.testvalidation.entrypoint.rest.handlerexception.mapper;

import com.grcp.testvalidation.entrypoint.rest.handlerexception.json.Error;
import com.grcp.testvalidation.entrypoint.rest.handlerexception.json.ErrorResponse;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Component
public class ErrorMapper {

    public ErrorResponse mapToErrorsResponse(BindingResult bindingResult) {
        List<Error> errors = bindingResult.getFieldErrors().stream()
                .map(this::buildError)
                .collect(Collectors.toList());

        return mapToErrorsResponse(errors);
    }

    private ErrorResponse mapToErrorsResponse(List<Error> errors) {
        return ErrorResponse.builder()
                .errors(errors)
                .build();
    }

    private Error buildError(FieldError fieldError) {
        return Error.builder()
                .field(fieldError.getField())
                .errorMessage(fieldError.getDefaultMessage())
                .build();
    }
}
