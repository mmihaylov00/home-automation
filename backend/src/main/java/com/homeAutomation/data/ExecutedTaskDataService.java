package com.homeAutomation.data;

import com.homeAutomation.data.repository.ExecutedTaskRepository;
import com.homeAutomation.extension.database.data.AbstractDataService;
import com.homeAutomation.extension.database.query.Query;
import com.homeAutomation.model.ExecutedTask;
import com.homeAutomation.model.enums.ExecutedTaskStatus;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ExecutedTaskDataService extends AbstractDataService<ExecutedTaskRepository, ExecutedTask, Long> {
    public List<ExecutedTask> findExecutable() {
        return find(Query.builder()
                .where("status", ExecutedTaskStatus.COMPLETED, Query.Comparator.NOT_EQUALS)
                .build()).list();
    }
}
