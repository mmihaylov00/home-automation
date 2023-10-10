package com.homeAutomation.api;

import com.homeAutomation.api.dto.AuthResponse;
import com.homeAutomation.service.user.UserService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

    @Inject
    UserService userService;

    @GET
    public AuthResponse deviceAuth() {
        return userService.authenticate();
    }
}
