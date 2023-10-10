package com.homeAutomation.service.task;

import com.homeAutomation.api.dto.DeviceResponse;
import com.homeAutomation.api.dto.TaskResponse;
import com.homeAutomation.data.DeviceDataService;
import com.homeAutomation.data.TaskDataService;
import com.homeAutomation.extension.context.Context;
import com.homeAutomation.mapper.DeviceMapper;
import com.homeAutomation.service.device.DeviceService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class TaskServiceImpl implements TaskService {
    @Inject
    TaskDataService taskDataService;

    public List<TaskResponse> list() {

    }
}
