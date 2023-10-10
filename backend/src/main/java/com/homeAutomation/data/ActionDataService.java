package com.homeAutomation.data;

import com.homeAutomation.extension.database.data.AbstractDataService;
import com.homeAutomation.model.Action;
import com.homeAutomation.model.Task;
import com.homeAutomation.repository.ActionRepository;
import com.homeAutomation.repository.TaskRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.UUID;

@ApplicationScoped
public class ActionDataService extends AbstractDataService<ActionRepository, Action, UUID> {
}
