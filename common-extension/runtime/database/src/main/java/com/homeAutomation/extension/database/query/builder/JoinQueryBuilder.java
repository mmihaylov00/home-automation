package com.homeAutomation.extension.database.query.builder;

import com.homeAutomation.extension.database.query.Query;

public interface JoinQueryBuilder extends GroupByBuilder {
    JoinQueryBuilder join(String table, String key, String foreignKey);

    JoinQueryBuilder join(Query.JoinType joinType, String table, String key, String foreignKey);

    WhereQueryBuilder select(String query);
    WhereQueryBuilder select(String... params);
}
