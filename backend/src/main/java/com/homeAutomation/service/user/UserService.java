package com.homeAutomation.service.user;

import com.homeAutomation.api.dto.AuthResponse;
import com.homeAutomation.model.User;

import java.util.UUID;

public interface UserService {
    User findById(UUID id);
    AuthResponse authenticate();
}
