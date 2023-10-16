package com.homeAutomation.extension.context;

import java.util.UUID;

public interface ContextUser {
    UUID getId();

    boolean isAdmin();
    boolean isRemoteAllowed();
}
