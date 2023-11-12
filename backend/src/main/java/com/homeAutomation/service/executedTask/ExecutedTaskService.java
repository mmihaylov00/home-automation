package com.homeAutomation.service.executedTask;

import com.homeAutomation.model.Task;
import com.homeAutomation.model.User;

public interface ExecutedTaskService {
    void create(Task task, User user);
}
