package com.homeAutomation.consumer;

import com.homeAutomation.extension.exception.AppException;
import com.homeAutomation.model.ExecutedTask;
import com.homeAutomation.model.enums.ExecutedTaskStatus;
import com.homeAutomation.service.action.ActionService;
import com.homeAutomation.service.executedTask.ExecutedTaskService;
import io.quarkus.scheduler.Scheduled;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class ExecutedTasksConsumer {

    @Inject
    ExecutedTaskService executedTaskService;
    @Inject
    ActionService actionService;

    @Scheduled(every = "0.5s")
    void consume() {
        List<ExecutedTask> tasks = executedTaskService.listExecutable();
        for (ExecutedTask task : tasks) {
            executeTask(task);
        }
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    void executeTask(ExecutedTask task) {
        ExecutedTaskStatus status = task.getStatus();
        if (status == ExecutedTaskStatus.PENDING) {
            task.setStatus(ExecutedTaskStatus.RUNNING);
            task.setCurrentAction(task.getTask().getStartAction());
        }

        try {
            if (actionService.executeCurrentAction(task)) {
                task.setCurrentAction(task.getCurrentAction().getNextAction());
            } else if (status != ExecutedTaskStatus.PENDING) {
                //skip saving if nothing has changed
                return;
            }
        } catch (AppException e) {
            task.setAdditionalInformation(String.format("Action %s failed: %s", task.getCurrentAction().getId(), e.getMessage()));
            task.setCurrentAction(task.getCurrentAction().getFailAction());
        }

        if (task.getCurrentAction() == null) {
            task.setStatus(ExecutedTaskStatus.COMPLETED);
        }

        executedTaskService.save(task);
    }
}
