package com.homeAutomation.actions;

import com.homeAutomation.model.enums.ActionType;
import com.homeAutomation.properties.BaseProperty;

import java.util.List;

public abstract class BaseAction {
    public abstract ActionType getType();

    public abstract boolean execute(List<BaseProperty> properties);
}
