package com.homeAutomation.properties;

import com.homeAutomation.extension.database.entity.LongIDEntity;
import com.homeAutomation.model.enums.PropertyType;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@MappedSuperclass
public class BaseProperty extends LongIDEntity {

    @Column(nullable = false)
    private String key;

    @Column(nullable = false)
    private String value;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PropertyType type;
}
