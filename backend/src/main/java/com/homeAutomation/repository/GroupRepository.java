package com.homeAutomation.repository;

import com.homeAutomation.model.Group;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GroupRepository implements PanacheRepositoryBase<Group, Long> {
}
