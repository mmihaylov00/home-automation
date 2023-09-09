package com.homeAutomation.extension.security.interceptor;

import com.homeAutomation.extension.context.Context;
import com.homeAutomation.extension.exception.AppException;
import com.homeAutomation.extension.exception.code.BaseErrorCode;
import io.quarkus.security.Authenticated;
import io.quarkus.security.identity.SecurityIdentity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.resteasy.reactive.server.ServerRequestFilter;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.ext.Provider;
import java.util.Objects;
import java.util.UUID;

@Provider
@Slf4j
public class ContextTokenPopulatingInterceptor {

    @Inject
    SecurityIdentity identity;

    @ServerRequestFilter(priority = Priorities.USER + 100)
    public void filter(ContainerRequestContext ctx, ResourceInfo resource) {
        Context context = Context.get();

        if (resource.getResourceClass().isAnnotationPresent(Authenticated.class) ||
                resource.getResourceMethod().isAnnotationPresent(Authenticated.class) ||
                resource.getResourceMethod().isAnnotationPresent(RolesAllowed.class))
            setTokenContext(context);

        //todo set other context data form headers
    }

    private void setTokenContext(Context context) {
        try {
            JsonWebToken principal = (JsonWebToken) identity.getPrincipal();
            if (Objects.isNull(principal)) return;

            String userId = principal.getClaim("user-id");
            if (StringUtils.isEmpty(userId)) return;
//
//            context.setUserId(UUID.fromString(userId));
//            String deviceId = principal.getClaim(SecurityConstants.JWT.DEVICE_ID);
//            if (deviceId != null) {
//                context.setDeviceId(UUID.fromString(deviceId));
//                context.setClientType(TokenType.APP);
//            } else {
//                String type = principal.getClaim(SecurityConstants.JWT.CLIENT_TYPE);
//                if (TokenType.INNER.name().equals(type)){
//                    context.setClientType(TokenType.INNER);
//                }
//            }
        } catch (ClassCastException ignored) {
        } catch (IllegalArgumentException e) {
            throw new AppException(BaseErrorCode.INVALID_VALUE, "Invalid Authorization Token");
        }
    }

}
