package com.homeAutomation.consumer;

import com.homeAutomation.extension.exception.AppException;
import com.homeAutomation.model.ExecutedTask;
import com.homeAutomation.model.enums.ExecutedTaskStatus;
import com.homeAutomation.service.action.ActionService;
import com.homeAutomation.service.executedTask.ExecutedTaskService;
import io.quarkus.scheduler.Scheduled;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class ExecutedTasksConsumer {

    @Inject
    ExecutedTaskService executedTaskService;
    @Inject
    ActionService actionService;

    @Scheduled(every = "500ms")
    void consume() {
        List<ExecutedTask> tasks = executedTaskService.listExecutable();
        for (ExecutedTask task : tasks) {
            if (task.getStatus() == ExecutedTaskStatus.PENDING) {
                task.setStatus(ExecutedTaskStatus.RUNNING);
                task.setCurrentAction(task.getTask().getStartAction());
            }

            try {
                if (actionService.executeCurrentAction(task)) {
                    task.setCurrentAction(task.getCurrentAction().getNextAction());
                }
            } catch (AppException e){
                task.setAdditionalInformation(String.format("Action %s failed: %s", task.getCurrentAction().getId(), e.getMessage()));
                task.setCurrentAction(task.getCurrentAction().getFailAction());
            }

            if (task.getCurrentAction() == null) {
                task.setStatus(ExecutedTaskStatus.COMPLETED);
            }

            executedTaskService.save(task);
        }
    }
}
