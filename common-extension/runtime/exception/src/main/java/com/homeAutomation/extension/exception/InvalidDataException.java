package com.homeAutomation.extension.exception;

import com.homeAutomation.extension.exception.response.ValidationErrorResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.Map;

@Getter
@AllArgsConstructor
public class InvalidDataException extends RuntimeException implements Serializable {
    public static final String DEFAULT_VALIDATION_MESSAGE = "Validation failed";
    public static final String CONSTRAINT_VIOLATION_ERROR_CODE = "VALIDATION_ERROR";

    private Map<String, String> messages;

    public ValidationErrorResponse toResponse() {
        ValidationErrorResponse response = new ValidationErrorResponse(DEFAULT_VALIDATION_MESSAGE);
        messages.forEach((key, value) -> response.addError(key, value, CONSTRAINT_VIOLATION_ERROR_CODE));
        return response;
    }

}