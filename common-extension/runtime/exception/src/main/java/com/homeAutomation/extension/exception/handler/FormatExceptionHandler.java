package com.homeAutomation.extension.exception.handler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.homeAutomation.extension.exception.code.BaseErrorCode;
import com.homeAutomation.extension.exception.response.ValidationErrorResponse;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Provider
public class FormatExceptionHandler implements ExceptionMapper<InvalidFormatException> {
    public static final String DEFAULT_FORMAT_MESSAGE = "Invalid payload format";
    public static final String INVALID_FORMAT_ERROR_CODE = "invalidFormat";
    public static final String NUMBER_TYPE_MESSAGE = "Expected number format";
    public static final String UUID_TYPE_MESSAGE = "Expected uuid format";
    public static final String DATE_TYPE_MESSAGE = "Expected date format";
    public static final String ENUM_TYPE_MESSAGE = "Expected enum format";
    public static final String UNKNOWN_TYPE_MESSAGE = "Invalid value format";

    @Override
    public Response toResponse(InvalidFormatException ex) {
        final BaseErrorCode code = BaseErrorCode.BAD_REQUEST;
        ValidationErrorResponse validationErrorResponse = new ValidationErrorResponse(DEFAULT_FORMAT_MESSAGE);

        validationErrorResponse.setErrors(List.of(
                new ValidationErrorResponse.ValidationError(getFieldTypeError(ex.getTargetType()),
                        ex.getPath().get(0).getFieldName(), INVALID_FORMAT_ERROR_CODE)
        ));

        return Response.status(code.httpStatus())
                .entity(validationErrorResponse)
                .build();
    }

    private String getFieldTypeError(Class<?> c) {
        if (Objects.equals(int.class, c) || Objects.equals(Integer.class, c) ||
                Objects.equals(Long.class, c) || Objects.equals(long.class, c) ||
                Objects.equals(BigDecimal.class, c)) {
            return NUMBER_TYPE_MESSAGE;
        } else if (Objects.equals(UUID.class, c)) {
            return UUID_TYPE_MESSAGE;
        } else if (Objects.equals(OffsetDateTime.class, c)) {
            return DATE_TYPE_MESSAGE;
        } else if (c.isEnum()) {
            //todo enum is not working for some reason
            return ENUM_TYPE_MESSAGE;
        }
        return UNKNOWN_TYPE_MESSAGE;
    }
}