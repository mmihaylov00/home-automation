package com.homeAutomation.mapper;

import com.homeAutomation.api.dto.DeviceResponse;
import com.homeAutomation.model.Device;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface DeviceMapper {
    DeviceResponse map(Device device);
}
