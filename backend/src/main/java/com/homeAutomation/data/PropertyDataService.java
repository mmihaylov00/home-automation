package com.homeAutomation.data;

import com.homeAutomation.extension.database.data.AbstractDataService;
import com.homeAutomation.model.Property;
import com.homeAutomation.model.Task;
import com.homeAutomation.repository.PropertyRepository;
import com.homeAutomation.repository.TaskRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.UUID;

@ApplicationScoped
public class PropertyDataService extends AbstractDataService<PropertyRepository, Property, Long> {
}
