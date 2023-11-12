package com.homeAutomation.service.executedTask;

import com.homeAutomation.data.ExecutedTaskDataService;
import com.homeAutomation.model.ExecutedTask;
import com.homeAutomation.model.Task;
import com.homeAutomation.model.User;
import com.homeAutomation.model.enums.ExecutedTaskStatus;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class ExecutedTaskServiceImpl implements ExecutedTaskService {
    @Inject
    ExecutedTaskDataService dataService;

    @Override
    public void create(Task task, User user) {
        dataService.save(ExecutedTask.builder()
                .status(ExecutedTaskStatus.PENDING)
                .task(task)
                .triggeredBy(user)
                .build());
    }

    @Override
    public List<ExecutedTask> listExecutable() {
        return dataService.findExecutable();
    }

    @Override
    public void save(ExecutedTask task) {
        dataService.save(task);
    }
}
