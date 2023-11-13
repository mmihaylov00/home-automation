package com.homeAutomation.extension.database.query.builder;

public interface GroupByBuilder extends OrderQueryBuilder {
    ThenGroupQueryBuilder group(String query);
}
