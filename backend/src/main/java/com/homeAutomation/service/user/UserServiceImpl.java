
package com.homeAutomation.service.user;

import com.homeAutomation.api.dto.AuthResponse;
import com.homeAutomation.data.UserDataService;
import com.homeAutomation.extension.context.Context;
import com.homeAutomation.extension.context.ContextUser;
import com.homeAutomation.extension.exception.AppException;
import com.homeAutomation.extension.exception.code.BaseErrorCode;
import com.homeAutomation.extension.security.SecurityTokenManager;
import com.homeAutomation.model.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.UUID;

@ApplicationScoped
public class UserServiceImpl implements UserService {
    @Inject
    UserDataService userDataService;

    @Inject
    SecurityTokenManager securityTokenManager;

    public User findById(UUID id) {
        return this.userDataService.findById(id);
    }

    @Override
    public AuthResponse authenticate() {
        Context context = Context.get();
        if (context.getUserId() == null) {
            if (context.getIp() == null || context.getLocalIp() == null) {
                throw new AppException(BaseErrorCode.UNAUTHORIZED, "Could not authenticate!");
            }
            context.setUser(User.builder()
                    .name(context.getDeviceName())
                    .localIp(context.getLocalIp())
                    .deviceInformation(context.getBrowserInformation())
                    .build());
        }

        return authenticate(context.getUser());
    }

    private AuthResponse authenticate(ContextUser user) {
        String token = securityTokenManager.generate(user.getId().toString(), user.isAdmin());

        return AuthResponse.builder()
                .token(token)
                .exp(SecurityTokenManager.TOKEN_EXPIRATION_TIME)
                .build();
    }
}
