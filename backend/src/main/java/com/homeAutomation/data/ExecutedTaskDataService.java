package com.homeAutomation.data;

import com.homeAutomation.extension.database.data.AbstractDataService;
import com.homeAutomation.model.ExecutedTask;
import com.homeAutomation.data.repository.ExecutedTaskRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ExecutedTaskDataService extends AbstractDataService<ExecutedTaskRepository, ExecutedTask, Long> {
}
