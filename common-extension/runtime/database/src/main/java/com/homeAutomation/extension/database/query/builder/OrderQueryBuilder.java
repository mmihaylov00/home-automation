package com.homeAutomation.extension.database.query.builder;

import com.homeAutomation.extension.database.pagination.PageRequest;

public interface OrderQueryBuilder extends BuildQueryBuilder {
    ThenOrderQueryBuilder orderBy(String paramName);

    ThenOrderQueryBuilder orderByDesc(String paramName);

    ThenOrderQueryBuilder orderBy(PageRequest pageRequest);

}
