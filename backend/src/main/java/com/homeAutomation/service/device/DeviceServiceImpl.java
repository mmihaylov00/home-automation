package com.homeAutomation.service.device;

import com.homeAutomation.api.dto.DeviceRequest;
import com.homeAutomation.api.dto.DeviceResponse;
import com.homeAutomation.data.DeviceDataService;
import com.homeAutomation.mapper.DeviceMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class DeviceServiceImpl implements DeviceService {

    @Inject
    DeviceMapper mapper;

    @Inject
    DeviceDataService deviceDataService;

    @Override
    public List<DeviceResponse> list() {
        return deviceDataService.all().stream().map(device -> mapper.map(device)).toList();
    }

    @Override
    public void create(UUID userId, DeviceRequest deviceRequest) {
        deviceDataService.save(mapper.map(deviceRequest));
    }
}
