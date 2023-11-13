package com.homeAutomation.extension.database.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@DynamicUpdate
@MappedSuperclass
/*
 * Please create a sequence and set it as a default
 * for the id column for each entity extending this class
 */
public abstract class LongIDEntity extends BaseEntity<Long> {
    @Id
    @Column(updatable = false, nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
}
