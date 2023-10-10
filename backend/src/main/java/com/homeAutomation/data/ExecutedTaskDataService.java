package com.homeAutomation.data;

import com.homeAutomation.extension.database.data.AbstractDataService;
import com.homeAutomation.model.Action;
import com.homeAutomation.model.ExecutedTask;
import com.homeAutomation.repository.ActionRepository;
import com.homeAutomation.repository.ExecutedTaskRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.UUID;

@ApplicationScoped
public class ExecutedTaskDataService extends AbstractDataService<ExecutedTaskRepository, ExecutedTask, Long> {
}
