package com.homeAutomation.extension.deployment;

import com.homeAutomation.extension.exception.InvalidDataException;
import com.homeAutomation.extension.exception.handler.BaseExceptionHandler;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.homeAutomation.extension.exception.AppException;
import com.homeAutomation.extension.exception.handler.*;
import io.quarkus.arc.ArcUndeclaredThrowableException;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.FeatureBuildItem;
import io.quarkus.resteasy.reactive.spi.ExceptionMapperBuildItem;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.NotAllowedException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Priorities;

public class ExceptionProcessor {

    private static final String FEATURE = "common-exception-extension";
    private static final int INVALID_JSON_EXCEPTION_PRIORITY = Priorities.USER;
    private static final int NOT_FOUND_EXCEPTION_PRIORITY = Priorities.USER;
    private static final int NOT_ALLOWED_EXCEPTION_PRIORITY = Priorities.USER;
    private static final int INVALID_FORMAT_EXCEPTION_PRIORITY = Priorities.USER + 100;
    private static final int VALIDATION_EXCEPTION_PRIORITY = Priorities.USER + 200;
    private static final int BASE_EXCEPTION_PRIORITY = Priorities.USER + 300;
    private static final int CONSTRAINT_EXCEPTION_PRIORITY = Priorities.USER + 400;
    private static final int RUNTIME_EXCEPTION_PRIORITY = Priorities.USER + 500;

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
    }

    @BuildStep
    ExceptionMapperBuildItem baseExceptionHandler() {
        return new ExceptionMapperBuildItem(BaseExceptionHandler.class.getName(), AppException.class.getName(),
                BASE_EXCEPTION_PRIORITY, true);
    }

    @BuildStep
    ExceptionMapperBuildItem runtimeExceptionHandler() {
        return new ExceptionMapperBuildItem(RuntimeExceptionHandler.class.getName(),
                RuntimeException.class.getName(), RUNTIME_EXCEPTION_PRIORITY, true);
    }

    @BuildStep
    ExceptionMapperBuildItem validationExceptionHandler() {
        return new ExceptionMapperBuildItem(ValidationExceptionHandler.class.getName(),
                ConstraintViolationException.class.getName(), VALIDATION_EXCEPTION_PRIORITY, true);
    }

    @BuildStep
    ExceptionMapperBuildItem invalidDataExceptionHandler() {
        return new ExceptionMapperBuildItem(InvalidDataExceptionHandler.class.getName(),
                InvalidDataException.class.getName(), VALIDATION_EXCEPTION_PRIORITY, true);
    }

    @BuildStep
    ExceptionMapperBuildItem invalidJsonExceptionHandler() {
        return new ExceptionMapperBuildItem(InvalidJsonExceptionHandler.class.getName(),
                JsonMappingException.class.getName(), INVALID_JSON_EXCEPTION_PRIORITY, true);
    }

    @BuildStep
    ExceptionMapperBuildItem invalidFormatExceptionHandler() {
        return new ExceptionMapperBuildItem(FormatExceptionHandler.class.getName(),
                InvalidFormatException.class.getName(), INVALID_FORMAT_EXCEPTION_PRIORITY, true);
    }

    @BuildStep
    ExceptionMapperBuildItem transactionExceptionHandler() {
        return new ExceptionMapperBuildItem(TransactionExceptionHandler.class.getName(),
                ArcUndeclaredThrowableException.class.getName(), CONSTRAINT_EXCEPTION_PRIORITY, true);
    }

    @BuildStep
    ExceptionMapperBuildItem notFoundExceptionHandler() {
        return new ExceptionMapperBuildItem(NotFoundExceptionHandler.class.getName(),
                NotFoundException.class.getName(), NOT_FOUND_EXCEPTION_PRIORITY, true);
    }

    @BuildStep
    ExceptionMapperBuildItem notAllowedExceptionHandler() {
        return new ExceptionMapperBuildItem(NotAllowedExceptionHandler.class.getName(),
                NotAllowedException.class.getName(), NOT_ALLOWED_EXCEPTION_PRIORITY, true);
    }
}
