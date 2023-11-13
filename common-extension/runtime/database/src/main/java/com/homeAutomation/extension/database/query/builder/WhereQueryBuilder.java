package com.homeAutomation.extension.database.query.builder;

import com.homeAutomation.extension.database.query.Query;

public interface WhereQueryBuilder extends OrderQueryBuilder, GroupByBuilder {
    AdditionalWhereQueryBuilder where(String paramName, Object value, Query.Comparator comparator);

    AdditionalWhereQueryBuilder where(String paramName, Object value);
    AdditionalWhereQueryBuilder where(String paramName, Object value, boolean isNullCheck);

    AdditionalWhereQueryBuilder whereNull(String paramName);

    AdditionalWhereQueryBuilder whereNotNull(String paramName);
}
