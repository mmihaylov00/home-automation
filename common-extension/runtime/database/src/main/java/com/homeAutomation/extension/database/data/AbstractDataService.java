package com.homeAutomation.extension.database.data;

import com.homeAutomation.extension.database.entity.BaseEntity;
import com.homeAutomation.extension.database.query.Query;
import com.homeAutomation.extension.exception.AppException;
import com.homeAutomation.extension.exception.code.BaseErrorCode;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Sort;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractDataService<Repository extends PanacheRepositoryBase<Entity, ID>,
        Entity extends BaseEntity<ID>,
        ID extends Serializable> {
    @Inject
    protected Repository repository;

    private String entityName;

    @Transactional
    public Entity save(Entity entity) {
        repository.persist(entity);
        return entity;
    }

    @Transactional
    public Entity saveAndFlush(Entity entity) {
        repository.persistAndFlush(entity);
        return entity;
    }

    @Transactional
    @SafeVarargs
    public final void saveAll(Entity... entities) {
        repository.persist(Arrays.stream(entities));
    }

    public Entity findById(ID id) {
        return repository.findByIdOptional(id).orElseThrow(() ->
                new AppException(BaseErrorCode.RESOURCE_NOT_FOUND, getEntityName() + " with ID " + id.toString() + " was not found"));
    }

    private String getEntityName() {
        if (entityName == null) {
            final String[] typeName = ((ParameterizedType) ((Class<?>) getClass().getGenericSuperclass())
                    .getGenericSuperclass()).getActualTypeArguments()[1].getTypeName().split("\\.");
            entityName = typeName[typeName.length - 1];
        }
        return entityName;
    }

    protected PanacheQuery<Entity> find(Query query) {
        return this.repository.find(query.getWhere(), query.getParams());
    }

    protected int delete(Query query) {
        return this.repository.update("deleted = true " + query.getWhere(), query.getParams());
    }

    protected int delete(Entity entity) {
        entity.delete();
        repository.persist(entity);
        return 1;
    }

    public List<Entity> all() {
        return repository.list("where deleted = false");
    }

    public List<Entity> all(Sort sort) {
        return repository.list("where deleted = false", sort);
    }
}
