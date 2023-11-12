package com.homeAutomation.data;

import com.homeAutomation.extension.database.data.AbstractDataService;
import com.homeAutomation.model.Device;
import com.homeAutomation.data.repository.DeviceRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DeviceDataService extends AbstractDataService<DeviceRepository, Device, Long> {
}
