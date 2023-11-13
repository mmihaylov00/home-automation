package com.homeAutomation.extension.database.query.builder;

public interface ThenOrderQueryBuilder extends BuildQueryBuilder {
    ThenOrderQueryBuilder thenOrderBy(String paramName);

    ThenOrderQueryBuilder thenOrderByDesc(String paramName);

}
