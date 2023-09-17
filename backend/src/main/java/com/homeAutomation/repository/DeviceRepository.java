package com.homeAutomation.repository;

import com.homeAutomation.model.Device;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DeviceRepository implements PanacheRepositoryBase<Device, Long> {
}
