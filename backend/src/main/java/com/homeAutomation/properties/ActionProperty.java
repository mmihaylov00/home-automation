package com.homeAutomation.properties;

import com.homeAutomation.model.ExecutedTask;
import com.homeAutomation.model.enums.PropertyType;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

@Getter
public enum ActionProperty {
    ACTION_START_TIME(PropertyType.NUMBER, (task) -> task.getModifiedDate().toInstant().toEpochMilli() + "");

    private final PropertyType type;
    private final Function<ExecutedTask, String> executedTaskFunction;

    ActionProperty(PropertyType type, Function<ExecutedTask, String> executedTaskFunction) {
        this.type = type;
        this.executedTaskFunction = executedTaskFunction;
    }

    public BaseProperty getProperty(ExecutedTask task) {
        return BaseProperty.builder()
                .key(name())
                .type(type)
                .value(executedTaskFunction.apply(task))
                .build();
    }

    public static List<BaseProperty> getSystemProperties(ExecutedTask task) {
        return Arrays.stream(ActionProperty.values()).map(actionProperty -> actionProperty.getProperty(task)).toList();
    }
}
