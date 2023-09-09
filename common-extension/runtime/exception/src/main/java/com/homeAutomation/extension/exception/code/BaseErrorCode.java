package com.homeAutomation.extension.exception.code;

import lombok.AllArgsConstructor;

import static javax.ws.rs.core.Response.*;

@AllArgsConstructor
public enum BaseErrorCode implements ErrorCode {
    UNEXPECTED_EXCEPTION(Status.INTERNAL_SERVER_ERROR),
    UNAUTHORIZED(Status.UNAUTHORIZED),
    BAD_REQUEST(Status.BAD_REQUEST),
    RESOURCE_NOT_FOUND(Status.NOT_FOUND),
    INVALID_VALUE(Status.BAD_REQUEST),
    SERVICE_UNAVAILABLE(Status.SERVICE_UNAVAILABLE),
    ;

    private final Status status;

    @Override
    public Status httpStatus() {
        return status;
    }
}
