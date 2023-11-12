package com.homeAutomation.data;

import com.homeAutomation.extension.database.data.AbstractDataService;
import com.homeAutomation.model.Action;
import com.homeAutomation.data.repository.ActionRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.UUID;

@ApplicationScoped
public class ActionDataService extends AbstractDataService<ActionRepository, Action, UUID> {
}
