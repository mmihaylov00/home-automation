package com.homeAutomation.api;

import com.homeAutomation.api.dto.TaskResponse;
import com.homeAutomation.service.task.TaskService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/task")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TaskController {

    @Inject
    TaskService taskService;

    @GET
    public List<TaskResponse> list() {
        return this.taskService.list();
    }
}
