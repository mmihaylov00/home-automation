package com.homeAutomation.extension.exception.handler;

import com.homeAutomation.extension.exception.code.BaseErrorCode;
import com.homeAutomation.extension.exception.response.ErrorResponse;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotFoundExceptionHandler implements ExceptionMapper<NotFoundException> {
    private static final String NOT_FOUND_MESSAGE = "Requested resource could not be found!";
    private static final BaseErrorCode NOT_FOUND = BaseErrorCode.RESOURCE_NOT_FOUND;

    @Override
    public Response toResponse(NotFoundException ex) {
        return Response.status(NOT_FOUND.httpStatus())
                .entity(new ErrorResponse(NOT_FOUND, NOT_FOUND_MESSAGE))
                .build();
    }
}