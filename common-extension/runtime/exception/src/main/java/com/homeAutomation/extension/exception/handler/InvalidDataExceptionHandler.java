package com.homeAutomation.extension.exception.handler;

import com.homeAutomation.extension.exception.InvalidDataException;
import com.homeAutomation.extension.exception.code.BaseErrorCode;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidDataExceptionHandler implements ExceptionMapper<InvalidDataException> {
    private static final BaseErrorCode code = BaseErrorCode.BAD_REQUEST;

    @Override
    public Response toResponse(InvalidDataException ex) {

        return Response.status(code.httpStatus())
                .entity(ex.toResponse())
                .build();
    }
}