package com.homeAutomation.service.executedTask;

import com.homeAutomation.model.ExecutedTask;
import com.homeAutomation.model.Task;
import com.homeAutomation.model.User;

import java.util.List;

public interface ExecutedTaskService {
    void create(Task task, User user);
    List<ExecutedTask> listExecutable();
    void save(ExecutedTask task);
}
