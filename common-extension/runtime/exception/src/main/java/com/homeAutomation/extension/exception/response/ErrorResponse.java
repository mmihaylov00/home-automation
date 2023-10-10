package com.homeAutomation.extension.exception.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.homeAutomation.extension.exception.AppException;
import com.homeAutomation.extension.exception.code.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ErrorResponse {

    private String errorCode;

    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private UUID id;

    public ErrorResponse(AppException e) {
        this(e.getErrorCode(), e.getMessage());
    }

    public ErrorResponse(Enum<? extends ErrorCode> errorCodeResponse, String message, UUID id) {
        this(errorCodeResponse, message);
        this.id = id;
    }

    public ErrorResponse(Enum<? extends ErrorCode> errorCodeResponse,
                         String message) {
        this.errorCode = errorCodeResponse.name();
        this.message = message;
    }
}
