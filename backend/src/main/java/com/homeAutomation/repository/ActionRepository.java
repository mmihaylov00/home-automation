package com.homeAutomation.repository;

import com.homeAutomation.model.Action;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import java.util.UUID;

@ApplicationScoped
public class ActionRepository implements PanacheRepositoryBase<Action, UUID> {
}
