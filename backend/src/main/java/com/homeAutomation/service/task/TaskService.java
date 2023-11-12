package com.homeAutomation.service.task;

import com.homeAutomation.api.dto.TaskResponse;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    List<TaskResponse> list();
    void execute(UUID id, UUID userId);
}
