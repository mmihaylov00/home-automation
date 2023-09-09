package com.homeAutomation.extension.database.pagination;

import lombok.Data;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

@Data
public class PageRequest {

    @DefaultValue("0")
    @QueryParam("pageNumber")
    private int pageNumber = 0;

    @DefaultValue("20")
    @QueryParam("pageSize")
    private int pageSize = 20;

}
