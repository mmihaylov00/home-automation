package com.homeAutomation.extension.context.interceptor;

import com.homeAutomation.extension.context.Context;
import org.jboss.resteasy.reactive.server.ServerResponseFilter;

import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.ext.Provider;

@Provider
public class ContextCleaningInterceptor {

    @ServerResponseFilter(priority = Priorities.USER)
    public void filter(ContainerResponseContext ctx, Throwable e, ResourceInfo res) {
        Context.clear();
    }
}
