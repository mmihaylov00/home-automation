package com.homeAutomation.model;

import com.homeAutomation.extension.database.entity.LongIDEntity;
import com.homeAutomation.model.enums.PropertyType;
import lombok.*;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "properties")
public class Property extends LongIDEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "action_id", nullable = false)
    private Action action;

    @Column(nullable = false)
    private String key;

    @Column(nullable = false)
    private String value;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PropertyType type;
}
