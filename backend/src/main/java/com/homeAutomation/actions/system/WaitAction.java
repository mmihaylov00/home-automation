package com.homeAutomation.actions.system;

import com.homeAutomation.actions.BaseAction;
import com.homeAutomation.model.Property;
import com.homeAutomation.model.enums.ActionType;

import java.util.List;

public class WaitAction extends BaseAction {
    @Override
    public ActionType getType() {
        return ActionType.WAIT;
    }

    @Override
    public boolean execute(List<Property> properties) {
        return false;
    }
}
