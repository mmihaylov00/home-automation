package com.homeAutomation.service.task;

import com.homeAutomation.api.dto.TaskResponse;
import com.homeAutomation.data.TaskDataService;
import com.homeAutomation.extension.context.Context;
import com.homeAutomation.mapper.TaskMapper;
import com.homeAutomation.model.Task;
import com.homeAutomation.model.User;
import com.homeAutomation.service.action.ActionService;
import com.homeAutomation.service.executedTask.ExecutedTaskService;
import com.homeAutomation.service.user.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class TaskServiceImpl implements TaskService {
    @Inject
    TaskDataService taskDataService;
    @Inject
    ActionService actionService;
    @Inject
    UserService userService;
    @Inject
    ExecutedTaskService executedTaskService;
    @Inject
    TaskMapper taskMapper;

    public List<TaskResponse> list() {
        return taskDataService.findByUserId(Context.get().getUserId())
                .stream().map(taskMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public void execute(UUID id, UUID userId) {
        User user = userService.findById(userId);
        Task task = taskDataService.findById(id);

        executedTaskService.create(task, user);
        actionService.execute(task.getStartAction());
    }

}
