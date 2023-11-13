package com.homeAutomation.extension.database.query.builder;

public interface BaseQueryBuilder extends WhereQueryBuilder, JoinQueryBuilder {
    JoinQueryBuilder from(String query);
}
