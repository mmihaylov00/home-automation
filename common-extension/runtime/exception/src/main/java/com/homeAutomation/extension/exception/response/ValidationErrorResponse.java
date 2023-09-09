package com.homeAutomation.extension.exception.response;

import com.homeAutomation.extension.exception.code.BaseErrorCode;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ValidationErrorResponse extends ErrorResponse {

    private List<ValidationError> errors;

    public ValidationErrorResponse(String message) {
        super(BaseErrorCode.BAD_REQUEST, message);
        errors = new ArrayList<>();
    }

    public void addError(String field, String message, String code){
        errors.add(new ValidationError(message, field, code));
    }

    @Getter
    @Setter
    public static class ValidationError {
        private String message;
        private String field = "body";
        private String code;

        public ValidationError(String message, String field, String code) {
            this.message = message;
            if(StringUtils.isNotEmpty(field)) this.field = field;
            this.code = code;
        }
    }
}