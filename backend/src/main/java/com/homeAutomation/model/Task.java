package com.homeAutomation.model;

import com.homeAutomation.extension.database.entity.UUIDEntity;
import com.homeAutomation.model.enums.TaskTrigger;
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
@Entity(name = "tasks")
public class Task extends UUIDEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TaskTrigger trigger;

    @Column(name = "trigger_value", nullable = false)
    private String triggerValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by_id", nullable = false)
    private User createdBy;

    @Column(name = "single_execution", nullable = false)
    @Builder.Default
    private boolean singleExecution = true;

    @OneToMany(mappedBy = "task", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Action> actions = Collections.emptyList();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "start_action_id", referencedColumnName = "id")
    private Action startAction;

    @OneToMany(mappedBy = "task", fetch = FetchType.LAZY)
    @Builder.Default
    private List<ExecutedTask> executedTasks = Collections.emptyList();
}
