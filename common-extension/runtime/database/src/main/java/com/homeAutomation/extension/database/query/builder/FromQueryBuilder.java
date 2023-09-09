package com.homeAutomation.extension.database.query.builder;

public interface FromQueryBuilder extends WhereQueryBuilder {
    JoinQueryBuilder from(String query);
}
