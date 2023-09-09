package com.homeAutomation.data;

import com.homeAutomation.extension.database.data.AbstractDataService;
import com.homeAutomation.extension.database.query.Query;
import com.homeAutomation.extension.exception.AppException;
import com.homeAutomation.extension.exception.code.BaseErrorCode;
import com.homeAutomation.model.Device;
import com.homeAutomation.repository.DeviceRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.UUID;

@ApplicationScoped
public class DeviceDataService extends AbstractDataService<DeviceRepository, Device, Long> {
}
