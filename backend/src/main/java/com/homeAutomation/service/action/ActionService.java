package com.homeAutomation.service.action;

import com.homeAutomation.consumer.FailedTaskException;
import com.homeAutomation.model.ExecutedTask;

public interface ActionService {
    boolean executeCurrentAction(ExecutedTask task) throws FailedTaskException;
}
