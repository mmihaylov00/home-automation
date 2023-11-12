package com.homeAutomation.actions;

import com.homeAutomation.model.Property;
import com.homeAutomation.model.enums.ActionType;

import java.util.List;

public abstract class BaseAction {
    public abstract ActionType getType();

    public abstract boolean execute(List<Property> properties);
}
