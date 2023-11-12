package com.homeAutomation.data;

import com.homeAutomation.extension.database.data.AbstractDataService;
import com.homeAutomation.model.Property;
import com.homeAutomation.data.repository.PropertyRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PropertyDataService extends AbstractDataService<PropertyRepository, Property, Long> {
}
