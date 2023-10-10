package com.homeAutomation.data;

import com.homeAutomation.extension.database.data.AbstractDataService;
import com.homeAutomation.model.User;
import com.homeAutomation.repository.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.UUID;

@ApplicationScoped
public class UserDataService extends AbstractDataService<UserRepository, User, UUID> {
}
