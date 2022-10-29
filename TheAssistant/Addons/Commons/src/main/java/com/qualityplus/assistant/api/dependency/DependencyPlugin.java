package com.qualityplus.assistant.api.dependency;

public interface DependencyPlugin {
    String getAddonName();

    default String getVersion(){
        return "1.0";
    }

    default boolean isEnabled(){
        return getAddonName() != null;
    }
}
