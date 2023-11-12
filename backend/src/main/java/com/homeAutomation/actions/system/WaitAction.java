package com.homeAutomation.actions.system;

import com.homeAutomation.actions.BaseAction;
import com.homeAutomation.model.enums.ActionType;
import com.homeAutomation.properties.BaseProperty;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class WaitAction extends BaseAction {
    @Override
    public ActionType getType() {
        return ActionType.WAIT;
    }

    @Override
    public boolean execute(List<BaseProperty> properties) {
        return false;
    }
}
