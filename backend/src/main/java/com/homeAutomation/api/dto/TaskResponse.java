package com.homeAutomation.api.dto;

import com.homeAutomation.model.enums.TaskTrigger;
import lombok.Data;

import java.util.UUID;

@Data
public class TaskResponse {
    private UUID id;
    private String name;
    private TaskTrigger trigger;
    private String triggerValue;
}
