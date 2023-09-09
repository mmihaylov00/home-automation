package com.homeAutomation.api;

import com.homeAutomation.api.dto.DeviceResponse;
import com.homeAutomation.extension.security.SecurityConstants;
import com.homeAutomation.service.device.DeviceService;
import io.quarkus.security.Authenticated;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/devices")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Authenticated
@RolesAllowed(SecurityConstants.Role.CLIENT_TYPE_ADMIN)
public class DeviceController {

    @Inject
    DeviceService deviceService;

    @GET
    public List<DeviceResponse> list() {
        return this.deviceService.list();
    }
}
