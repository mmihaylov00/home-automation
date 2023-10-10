package com.homeAutomation.data;

import com.homeAutomation.extension.database.data.AbstractDataService;
import com.homeAutomation.extension.database.query.Query;
import com.homeAutomation.model.User;
import com.homeAutomation.repository.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class UserDataService extends AbstractDataService<UserRepository, User, UUID> {

    public Optional<User> findByIp(String ip) {
        return find(Query.builder("localIp", ip).build()).firstResultOptional();
    }
}
