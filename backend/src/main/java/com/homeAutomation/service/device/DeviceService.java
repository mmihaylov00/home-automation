package com.homeAutomation.service.device;

import com.homeAutomation.api.dto.DeviceResponse;

import java.util.List;
import java.util.UUID;

public interface DeviceService {
    List<DeviceResponse> list();

    void create(UUID userId);
}
