
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
        User user = this.userDataService.findById(id);
        if (user.isBlocked()) {
            throw new AppException(BaseErrorCode.UNAUTHORIZED, "User is blocked");
        }
        return user;
    }

    @Override
    public AuthResponse authenticate() {
        Context context = Context.get();
        if (context.getUserId() == null) {
            if (context.getIp() == null) {
                throw new AppException(BaseErrorCode.UNAUTHORIZED, "Could not authenticate");
            }

            User user = userDataService.findByIp(context.getIp())
                    .orElse(userDataService.save(
                            User.builder()
                                    .name(context.getDeviceName())
                                    .localIp(context.getIp())
                                    .deviceInformation(context.getBrowserInformation())
                                    .build()
                    ));


            context.setUser(user);

            //todo reserve this device's IP in the router
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
