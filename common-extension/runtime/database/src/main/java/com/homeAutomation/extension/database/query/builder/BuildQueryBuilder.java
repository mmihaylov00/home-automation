package com.homeAutomation.extension.database.query.builder;

import com.homeAutomation.extension.database.query.Query;

public interface BuildQueryBuilder {

    Query build();

    Query build(int offset);
}
