package com.homeAutomation.extension.database.query.builder;

import com.homeAutomation.extension.database.query.Query;

public interface OrQueryBuilder {
    OrQueryBuilder or(String paramName, Object value);

    OrQueryBuilder or(Query query);

    OrQueryBuilder or(String paramName, Object value, Query.Comparator comparator);

    OrQueryBuilder orNull(String paramName);

    OrQueryBuilder orNotNull(String paramName);

    Query build();
}
