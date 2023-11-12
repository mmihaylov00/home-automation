package com.homeAutomation.data.repository;

import com.homeAutomation.model.ExecutedTask;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ExecutedTaskRepository implements PanacheRepositoryBase<ExecutedTask, Long> {
}
