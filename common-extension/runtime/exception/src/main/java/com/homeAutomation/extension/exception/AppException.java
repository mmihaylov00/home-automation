package com.homeAutomation.extension.exception;

import com.homeAutomation.extension.exception.code.BaseErrorCode;
import com.homeAutomation.extension.exception.code.ErrorCode;
import com.homeAutomation.extension.exception.response.ErrorResponse;
import lombok.Getter;

import javax.ws.rs.core.Response;
import java.io.Serializable;

@Getter
public class AppException extends RuntimeException implements Serializable, ErrorCode {

    private final Enum<? extends ErrorCode> errorCode;

    public AppException(Enum<? extends ErrorCode> errorCode) {
        this(errorCode, null, null);
    }

    public AppException(Enum<? extends ErrorCode> errorCode, Throwable cause) {
        this(errorCode, null, cause);
    }

    public AppException(Enum<? extends ErrorCode> errorCode, String message) {
        this(errorCode, message, null);
    }

    public AppException(Enum<? extends ErrorCode> errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public static AppException fromResponse(ErrorResponse response) {
        BaseErrorCode code = BaseErrorCode.valueOf(response.getErrorCode());
        return new AppException(code, response.getMessage());
    }

    @Override
    public String name() {
        return errorCode.name();
    }

    @Override
    public Response.Status httpStatus() {
        return ((ErrorCode) errorCode).httpStatus();
    }
}