package com.homeAutomation.extension.exception.handler;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.homeAutomation.extension.exception.code.BaseErrorCode;
import com.homeAutomation.extension.exception.response.ErrorResponse;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidJsonExceptionHandler implements ExceptionMapper<JsonMappingException> {
    public static final String DEFAULT_RESPONSE_MESSAGE = "Invalid request body";
    private static final BaseErrorCode code = BaseErrorCode.BAD_REQUEST;

    @Override
    public Response toResponse(JsonMappingException ex) {
        return Response.status(code.httpStatus())
                .entity(new ErrorResponse(code, DEFAULT_RESPONSE_MESSAGE))
                .build();
    }
}