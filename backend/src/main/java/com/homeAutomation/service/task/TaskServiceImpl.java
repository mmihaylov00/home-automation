package com.homeAutomation.service.task;

import com.homeAutomation.api.dto.TaskResponse;
import com.homeAutomation.data.TaskDataService;
import com.homeAutomation.extension.context.Context;
import com.homeAutomation.mapper.TaskMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class TaskServiceImpl implements TaskService {
    @Inject
    TaskDataService taskDataService;
    @Inject
    TaskMapper taskMapper;

    public List<TaskResponse> list() {
        return taskDataService.findByUserId(Context.get().getUserId())
                .stream().map(taskMapper::map)
                .collect(Collectors.toList());
    }
}
