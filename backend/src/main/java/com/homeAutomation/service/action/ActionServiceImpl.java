package com.homeAutomation.service.action;

import com.homeAutomation.actions.BaseAction;
import com.homeAutomation.model.Action;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class ActionServiceImpl implements ActionService {

    @Inject
    List<BaseAction> actions;

    @Override
    public void execute(Action action) {

    }
}
