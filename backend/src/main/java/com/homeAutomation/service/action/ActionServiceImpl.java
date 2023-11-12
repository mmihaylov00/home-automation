package com.homeAutomation.service.action;

import com.homeAutomation.actions.BaseAction;
import com.homeAutomation.consumer.FailedTaskException;
import com.homeAutomation.model.Action;
import com.homeAutomation.model.ExecutedTask;
import com.homeAutomation.properties.ActionProperty;
import com.homeAutomation.properties.BaseProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ActionServiceImpl implements ActionService {

    @Inject
    List<BaseAction> actions;

    @Override
    public boolean executeCurrentAction(ExecutedTask task) throws FailedTaskException {
        Action action = task.getCurrentAction();

        BaseAction baseAction = actions.stream().filter(b -> b.getType() == action.getType()).findFirst()
                .orElseThrow(() -> new FailedTaskException("Could not find an action with type " + action.getType()));

        List<BaseProperty> properties = new ArrayList<>(action.getProperties());
        properties.addAll(ActionProperty.getSystemProperties(task));

        return baseAction.execute(properties);
    }
}
