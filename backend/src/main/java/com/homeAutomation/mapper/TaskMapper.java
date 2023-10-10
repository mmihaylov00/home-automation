package com.homeAutomation.mapper;

import com.homeAutomation.api.dto.TaskResponse;
import com.homeAutomation.model.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface TaskMapper {
    TaskResponse map(Task task);
}
