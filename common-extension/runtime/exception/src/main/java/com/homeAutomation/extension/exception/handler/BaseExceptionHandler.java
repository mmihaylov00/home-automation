package com.homeAutomation.extension.exception.handler;

import com.homeAutomation.extension.exception.AppException;
import com.homeAutomation.extension.exception.response.ErrorResponse;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class BaseExceptionHandler implements ExceptionMapper<AppException> {
    @Override
    public Response toResponse(AppException e) {
        return Response.status(e.httpStatus())
                .entity(new ErrorResponse(e))
                .build();
    }
}
