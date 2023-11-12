package com.homeAutomation.api.dto;

import com.homeAutomation.model.enums.DeviceProvider;
import com.homeAutomation.model.enums.DeviceType;
import lombok.Data;

@Data
public class DeviceRequest {
    private String name;
    private DeviceType type;
    private DeviceProvider provider;
}
