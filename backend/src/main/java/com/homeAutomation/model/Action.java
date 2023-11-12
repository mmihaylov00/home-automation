package com.homeAutomation.model;

import com.homeAutomation.extension.database.entity.UUIDEntity;
import com.homeAutomation.model.enums.ActionType;
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
@Entity(name = "actions")
public class Action extends UUIDEntity {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ActionType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    private Task task;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "next_action_id", referencedColumnName = "id")
    private Action nextAction;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fail_action_id", referencedColumnName = "id")
    private Action failAction;

    @OneToMany(mappedBy = "action", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Property> properties = Collections.emptyList();
}
