package com.homeAutomation.extension.exception.handler;

import com.homeAutomation.extension.exception.code.BaseErrorCode;
import com.homeAutomation.extension.exception.logger.InternalErrorLogger;
import com.homeAutomation.extension.exception.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.NotAllowedException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
@Slf4j
public class RuntimeExceptionHandler extends InternalErrorLogger implements ExceptionMapper<RuntimeException> {
    private static final String NOT_FOUND_MESSAGE = "Requested resource could not be found!";
    private static final BaseErrorCode NOT_FOUND = BaseErrorCode.RESOURCE_NOT_FOUND;
    @Override
    public Response toResponse(RuntimeException e) {
        if (e instanceof NotAllowedException ||
                e instanceof NotFoundException)
            return Response.status(NOT_FOUND.httpStatus())
                    .entity(new ErrorResponse(NOT_FOUND, NOT_FOUND_MESSAGE))
                    .build();
        return handle(e);
    }
}
