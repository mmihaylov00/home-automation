package com.homeAutomation.extension.database.query.builder;

import com.homeAutomation.extension.database.query.Query;

import java.util.Collection;
import java.util.Set;

public interface AndQueryBuilder extends GroupByBuilder {
    AndQueryBuilder and(String paramName, Object value);
    AndQueryBuilder and(String paramName, Object value, boolean isNullCheck);

    AndQueryBuilder and(Query query);

    AndQueryBuilder and(String paramName, Object value, Query.Comparator comparator);

    AndQueryBuilder andNot(String paramName, Object value);
    AndQueryBuilder andNot(String paramName, Object value, boolean isNullCheck);

    AndQueryBuilder andNull(String paramName);

    AndQueryBuilder andNotNull(String paramName);

    AndQueryBuilder andContains(Collection<String> columns, Object expected);

    <T> AndQueryBuilder andContains(String paramName, Collection<T> values);
}
