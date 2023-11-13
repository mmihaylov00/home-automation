package com.homeAutomation.extension.database.query.builder;

public interface ThenGroupQueryBuilder extends OrderQueryBuilder {
    ThenGroupQueryBuilder thenGroupBy(String query);
}
