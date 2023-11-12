package com.homeAutomation.data;

import com.homeAutomation.extension.database.data.AbstractDataService;
import com.homeAutomation.extension.database.query.Query;
import com.homeAutomation.model.Task;
import com.homeAutomation.data.repository.TaskRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class TaskDataService extends AbstractDataService<TaskRepository, Task, UUID> {

    public List<Task> findByUserId(UUID userId) {
        return find(Query.builder("created_by_id", userId).build())
                .list();
    }
}
