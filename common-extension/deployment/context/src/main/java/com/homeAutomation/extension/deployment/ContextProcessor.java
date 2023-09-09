package com.homeAutomation.extension.deployment;

import com.homeAutomation.extension.context.interceptor.ContextCleaningInterceptor;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.FeatureBuildItem;
import io.quarkus.resteasy.reactive.spi.CustomContainerRequestFilterBuildItem;

class ContextProcessor {

    private static final String FEATURE = "common-context-extension";

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
    }

    @BuildStep
    CustomContainerRequestFilterBuildItem contextCleaningInterceptor() {
        return new CustomContainerRequestFilterBuildItem(ContextCleaningInterceptor.class.getName());
    }
}
