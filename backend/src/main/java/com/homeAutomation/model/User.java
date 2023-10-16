package com.homeAutomation.model;

import com.homeAutomation.extension.context.ContextUser;
import com.homeAutomation.extension.database.entity.UUIDEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Collections;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "users")
public class User extends UUIDEntity implements ContextUser {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "device_information", nullable = false)
    private String deviceInformation;

    @Column(name = "local_ip", nullable = false)
    private String localIp;

    @Builder.Default
    @Column(name = "is_admin", nullable = false)
    private boolean isAdmin = false;

    @Builder.Default
    @Column(name = "remote_allowed", nullable = false)
    private boolean remoteAllowed = false;

    @OneToMany(mappedBy = "createdBy", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Task> tasks = Collections.emptyList();

    @OneToMany(mappedBy = "triggeredBy", fetch = FetchType.LAZY)
    @Builder.Default
    private List<ExecutedTask> executedTasks = Collections.emptyList();
}
