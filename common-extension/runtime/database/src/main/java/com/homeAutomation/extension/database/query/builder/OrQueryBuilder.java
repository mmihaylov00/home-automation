package com.homeAutomation.extension.database.query.builder;

import com.homeAutomation.extension.database.query.Query;

import java.util.Collection;

public interface OrQueryBuilder extends GroupByBuilder {
    OrQueryBuilder or(String paramName, Object value);

    OrQueryBuilder or(String paramName, Object value, boolean isNullCheck);

    OrQueryBuilder or(Query query);

    OrQueryBuilder or(String paramName, Object value, Query.Comparator comparator);

    OrQueryBuilder orNot(String paramName, Object value);

    OrQueryBuilder orNot(String paramName, Object value, boolean isNullCheck);

    OrQueryBuilder orNull(String paramName);

    OrQueryBuilder orNotNull(String paramName);

    AndQueryBuilder orContains(Collection<String> columns, Object expected);

    <T> AndQueryBuilder orContains(String paramName, Collection<T> values);
}
