package com.homeAutomation.extension.database.entity;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@DynamicUpdate
@MappedSuperclass
public abstract class LongIDEntity extends BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(updatable = false, nullable = false)
    private Long id;
}
