package com.grcp.testvalidation.entrypoint.rest.handlerexception;

import com.grcp.testvalidation.entrypoint.rest.handlerexception.mapper.ErrorMapper;
import com.grcp.testvalidation.entrypoint.rest.handlerexception.json.ErrorResponse;
import com.grcp.testvalidation.entrypoint.rest.handlerexception.json.Error;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolationException;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Primary
@ControllerAdvice
public class CustomExceptionHandler {

    private final ErrorMapper errorMapper;

    public CustomExceptionHandler(ErrorMapper errorMapper) {
        this.errorMapper = errorMapper;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(BAD_REQUEST)
    ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
        return ResponseEntity.badRequest()
                .body("Error from ConstraintViolationException. Not valid due to validation error: " + e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ErrorResponse errorResponse = errorMapper.mapToErrorsResponse(e.getBindingResult());
        return ResponseEntity.badRequest()
                .body(errorResponse);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(BAD_REQUEST)
    ResponseEntity<String> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        return ResponseEntity.badRequest()
                .body("Error from MissingServletRequestParameterException. Not valid due to validation error: " + e.getMessage());
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(BAD_REQUEST)
    ResponseEntity<String> handleBindException(BindException e) {
        return ResponseEntity.badRequest()
                .body("Error from MissingServletRequestParameterException. Not valid due to validation error: " + e.getMessage());
    }
}
