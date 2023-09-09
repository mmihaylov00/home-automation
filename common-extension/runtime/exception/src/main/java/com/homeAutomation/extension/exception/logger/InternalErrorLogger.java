package com.homeAutomation.extension.exception.logger;

import com.homeAutomation.extension.exception.code.BaseErrorCode;
import com.homeAutomation.extension.exception.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.core.Response;
import java.util.UUID;

@Slf4j
public abstract class InternalErrorLogger {
    private static final String INTERNAL_SERVER_ERROR_MESSAGE = "Something went wrong";
    private static final BaseErrorCode CODE = BaseErrorCode.UNEXPECTED_EXCEPTION;

    public Response handle(Throwable e) {
        UUID uuid = UUID.randomUUID();
        log.error("Internal error: {}", uuid);
        log.error("Message: ", e);
        return Response.status(CODE.httpStatus())
                .entity(new ErrorResponse(CODE, INTERNAL_SERVER_ERROR_MESSAGE, uuid))
                .build();
    }
}
