package com.homeAutomation.model;

import com.homeAutomation.extension.database.entity.LongIDEntity;
import com.homeAutomation.model.enums.ExecutedTaskStatus;
import lombok.*;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "executed_tasks")
public class ExecutedTask extends LongIDEntity {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ExecutedTaskStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "current_action_id")
    private Action currentAction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "triggered_by_id", nullable = false)
    private User triggeredBy;

    @Column(name = "additional_information")
    private String additionalInformation;
}
