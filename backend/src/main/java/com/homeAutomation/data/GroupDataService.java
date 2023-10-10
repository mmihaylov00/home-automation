package com.homeAutomation.data;

import com.homeAutomation.extension.database.data.AbstractDataService;
import com.homeAutomation.model.Group;
import com.homeAutomation.repository.GroupRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GroupDataService extends AbstractDataService<GroupRepository, Group, Long> {
}
