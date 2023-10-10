package com.homeAutomation.service.task;

import com.homeAutomation.api.dto.TaskResponse;

import java.util.List;

public interface TaskService {
    List<TaskResponse> list();
}
