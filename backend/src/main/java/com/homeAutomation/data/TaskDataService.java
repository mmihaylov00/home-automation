package com.homeAutomation.data;

import com.homeAutomation.extension.database.data.AbstractDataService;
import com.homeAutomation.model.Device;
import com.homeAutomation.model.Task;
import com.homeAutomation.repository.DeviceRepository;
import com.homeAutomation.repository.TaskRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.UUID;

@ApplicationScoped
public class TaskDataService extends AbstractDataService<TaskRepository, Task, UUID> {
}
