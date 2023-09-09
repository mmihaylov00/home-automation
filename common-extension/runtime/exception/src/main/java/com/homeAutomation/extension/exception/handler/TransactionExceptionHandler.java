package com.homeAutomation.extension.exception.handler;

import com.homeAutomation.extension.exception.logger.InternalErrorLogger;
import io.quarkus.arc.ArcUndeclaredThrowableException;

import javax.transaction.RollbackException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class TransactionExceptionHandler extends InternalErrorLogger implements ExceptionMapper<ArcUndeclaredThrowableException> {
    @Override
    public Response toResponse(ArcUndeclaredThrowableException e) {
        Throwable cause = e.getCause();
        while (cause != null) {
            if (cause instanceof RollbackException) return handle(cause);
            cause = cause.getCause();
        }

        return handle(e.getCause());
    }
}
