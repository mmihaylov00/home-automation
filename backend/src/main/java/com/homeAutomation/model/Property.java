package com.homeAutomation.model;

import com.homeAutomation.properties.BaseProperty;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "properties")
public class Property extends BaseProperty {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "action_id", nullable = false)
    private Action action;
}
