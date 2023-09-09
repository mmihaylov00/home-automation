package com.homeAutomation.extension.database.query.builder;

import com.homeAutomation.extension.database.query.Query;

import java.util.Set;

public interface AndQueryBuilder {
    AndQueryBuilder and(String paramName, Object value);
    AndQueryBuilder and(Query query);
    AndQueryBuilder and(String paramName, Object value, Query.Comparator comparator);
    AndQueryBuilder andNull(String paramName);
    AndQueryBuilder andNotNull(String paramName);
    AndQueryBuilder andNotDeleted();
    AndQueryBuilder andContains(Set<String> columns, Object expected);
    Query build();
}
