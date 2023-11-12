package com.homeAutomation.queue;

import com.homeAutomation.service.executedTask.ExecutedTaskService;
import io.quarkus.scheduler.Scheduled;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ExecutedTasksConsumer {

    @Inject
    ExecutedTaskService executedTaskService;

    @Scheduled(every = "500ms")
    void consume() {

    }
}
