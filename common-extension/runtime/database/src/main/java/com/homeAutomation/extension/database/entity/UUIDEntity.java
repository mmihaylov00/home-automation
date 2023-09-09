package com.homeAutomation.extension.database.entity;


import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@MappedSuperclass
@SuperBuilder
public abstract class UUIDEntity extends BaseEntity<UUID> {
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(generator = "customGenerator")
    @GenericGenerator(name = "customGenerator", strategy = "com.homeAutomation.extension.database.generator.UseExistingOrGenerateIdGenerator")
    private UUID id;
}
