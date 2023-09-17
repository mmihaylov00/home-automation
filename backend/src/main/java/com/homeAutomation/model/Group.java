package com.homeAutomation.model;

import com.homeAutomation.extension.database.entity.LongIDEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "groups")
public class Group extends LongIDEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "groups")
    @Builder.Default
    private List<Device> devices = Collections.emptyList();
}
