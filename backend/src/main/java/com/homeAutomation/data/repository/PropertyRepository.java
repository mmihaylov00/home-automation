package com.homeAutomation.data.repository;

import com.homeAutomation.model.Property;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PropertyRepository implements PanacheRepositoryBase<Property, Long> {
}
