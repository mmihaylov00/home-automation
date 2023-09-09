package com.homeAutomation.model;

import com.homeAutomation.extension.database.entity.LongIDEntity;
import com.homeAutomation.model.enums.DeviceProvider;
import com.homeAutomation.model.enums.DeviceType;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "device")
public class Device extends LongIDEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private DeviceType type;

    @Column(name = "mac_address", nullable = false)
    private String macAddress;

    @Column(name = "mac_address", nullable = false)
    private DeviceProvider provider;
}
