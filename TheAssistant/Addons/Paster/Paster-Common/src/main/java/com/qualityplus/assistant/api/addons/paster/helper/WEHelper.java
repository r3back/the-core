package com.qualityplus.assistant.api.addons.paster.helper;

import com.qualityplus.assistant.api.dependency.resolver.DependencyResolver;

public interface WEHelper {
    default boolean isAsync(DependencyResolver resolver){
        return resolver.isPlugin("FastAsyncWorldEdit") || resolver.isPlugin("AsyncWorldEdit");
    }
}
