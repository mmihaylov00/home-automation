package com.homeAutomation.extension.deployment;

import com.homeAutomation.extension.security.SecurityTokenManager;
import com.homeAutomation.extension.security.interceptor.ContextTokenPopulatingInterceptor;
import io.quarkus.arc.deployment.AdditionalBeanBuildItem;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.FeatureBuildItem;
import io.quarkus.resteasy.reactive.spi.CustomContainerRequestFilterBuildItem;

public class SecurityProcessor {

    private static final String FEATURE = "common-security-extension";

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
    }

    @BuildStep
    CustomContainerRequestFilterBuildItem contextTokenPopulatingInterceptor() {
        return new CustomContainerRequestFilterBuildItem(ContextTokenPopulatingInterceptor.class.getName());
    }

    @BuildStep
    AdditionalBeanBuildItem tokenManager() {
        return new AdditionalBeanBuildItem(SecurityTokenManager.class);
    }
}
