package com.homeAutomation.extension.exception.handler;

import com.homeAutomation.extension.exception.InvalidDataException;
import com.homeAutomation.extension.exception.code.BaseErrorCode;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Provider
public class ValidationExceptionHandler implements ExceptionMapper<ConstraintViolationException> {
    private static final BaseErrorCode code = BaseErrorCode.BAD_REQUEST;

    @Override
    public Response toResponse(ConstraintViolationException ex) {
        Map<String, String> messages = new HashMap<>();
        ex.getConstraintViolations().forEach(violation -> messages.put(getKey(violation), violation.getMessage()));

        return Response.status(code.httpStatus())
                .entity(new InvalidDataException(messages).toResponse())
                .build();
    }

    private String getKey(ConstraintViolation<?> violation) {
        return Arrays.stream(violation.getPropertyPath().toString().split("\\."))
                .skip(2).collect(Collectors.joining("."));
    }
}