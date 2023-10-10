package com.homeAutomation.filter;

import com.homeAutomation.extension.context.Context;
import com.homeAutomation.extension.exception.AppException;
import com.homeAutomation.extension.exception.code.BaseErrorCode;
import com.homeAutomation.service.user.UserService;
import org.jboss.resteasy.reactive.server.ServerRequestFilter;

import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.ext.Provider;
import java.util.UUID;

@Provider
public class ContextUserPopulationInterceptor {

    @Inject
    UserService userService;

    @ServerRequestFilter(priority = Priorities.USER + 200)
    public void filter(ContainerRequestContext ctx, ResourceInfo resource) {
        Context context = Context.get();
        UUID userId = context.getUserId();
        if (userId == null) return;

        try {
            context.setUser(userService.findById(userId));
        } catch (AppException e) {
            throw new AppException(BaseErrorCode.INVALID_VALUE, "Invalid Authorization Token");
        }
    }
}
