package com.homeAutomation.model;

import com.homeAutomation.extension.database.entity.UUIDEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "users")
public class User extends UUIDEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "device_information", nullable = false)
    private String deviceInformation;

    @Column(name = "mac_address", nullable = false)
    private String macAddress;

    @Builder.Default
    @Column(name = "is_admin", nullable = false)
    private boolean isAdmin = false;

    @OneToMany(mappedBy = "createdBy", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Task> tasks = Collections.emptyList();

    @OneToMany(mappedBy = "triggeredBy", fetch = FetchType.LAZY)
    @Builder.Default
    private List<ExecutedTask> executedTasks = Collections.emptyList();
}
