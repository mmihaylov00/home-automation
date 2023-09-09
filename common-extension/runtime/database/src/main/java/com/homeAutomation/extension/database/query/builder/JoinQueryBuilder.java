package com.homeAutomation.extension.database.query.builder;

import com.homeAutomation.extension.database.query.Query;

public interface JoinQueryBuilder {
    JoinQueryBuilder join(String table, String key, String foreignKey);

    JoinQueryBuilder join(Query.JoinType joinType, String table, String key, String foreignKey);

    GroupQueryBuilder select(String query);
}
