package com.homeAutomation.extension.security;

import com.homeAutomation.extension.exception.AppException;
import com.homeAutomation.extension.exception.code.BaseErrorCode;
import io.smallrye.jwt.auth.principal.JWTParser;
import io.smallrye.jwt.auth.principal.ParseException;
import io.smallrye.jwt.build.Jwt;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collections;
import java.util.HashSet;

@ApplicationScoped
public class SecurityTokenManager {

    @ConfigProperty(name = "mp.jwt.verify.issuer")
    String issuer;

    @Inject
    JWTParser parser;

    // 1 week
    public static long TOKEN_EXPIRATION_TIME = 604_800L;

    public String generate(String id, boolean isAdmin) {
        final String role = isAdmin ?
                SecurityConstants.Role.CLIENT_TYPE_ADMIN : SecurityConstants.Role.CLIENT_TYPE_USER;
        return Jwt.issuer(issuer)
                .upn(id)
                .claim(SecurityConstants.JWT.CLIENT_TYPE_KEY, role)
                .groups(new HashSet<>(Collections.singletonList(role)))
                .expiresIn(TOKEN_EXPIRATION_TIME)
                .sign();
    }

    public JsonWebToken parse(String token) {
        try {
            return parser.parse(token);
        } catch (ParseException e) {
            throw new AppException(BaseErrorCode.UNAUTHORIZED, "Invalid bearer token");
        }
    }
}
