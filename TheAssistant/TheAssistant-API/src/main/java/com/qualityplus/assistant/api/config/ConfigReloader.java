package com.qualityplus.assistant.api.config;

import eu.okaeri.configs.OkaeriConfig;

import java.util.Arrays;

public interface ConfigReloader{
    void reloadFiles();

    default void reloadAll(OkaeriConfig... files){
        Arrays.stream(files).forEach(OkaeriConfig::load);
    }
}
