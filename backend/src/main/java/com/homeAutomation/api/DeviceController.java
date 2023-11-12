package com.homeAutomation.api;

import com.homeAutomation.api.dto.DeviceRequest;
import com.homeAutomation.api.dto.DeviceResponse;
import com.homeAutomation.extension.context.Context;
import com.homeAutomation.extension.security.SecurityConstants;
import com.homeAutomation.service.device.DeviceService;
import io.quarkus.vertx.web.Body;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/devices")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed(SecurityConstants.Role.CLIENT_TYPE_ADMIN)
public class DeviceController {

    @Inject
    DeviceService deviceService;

    @GET
    public List<DeviceResponse> list() {
        return this.deviceService.list();
    }

    @POST
    public void create(@Body DeviceRequest request) {
        deviceService.create(Context.get().getUserId(), request);
    }

}
