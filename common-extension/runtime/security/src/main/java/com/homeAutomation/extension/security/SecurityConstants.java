package com.homeAutomation.extension.security;

public class SecurityConstants {
    public interface JWT {
        String CLIENT_TYPE_KEY = "client-type";
        String USER_ID_KEY = "upn";
    }

    public interface Role {
        String CLIENT_TYPE_USER = "user";
        String CLIENT_TYPE_ADMIN = "admin";
    }
}
