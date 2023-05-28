package com.qualityplus.assistant.api.config;

import eu.okaeri.configs.OkaeriConfig;

import java.util.Arrays;

/**
 * Config files reloader
 */
public interface ConfigReloader{
    /**
     * Reload all files
     */
    public void reloadFiles();

    /**
     * Reload specific files
     *
     * @param files {@link OkaeriConfig} files to be reloaded
     */
    public default void reloadAll(OkaeriConfig... files){
        Arrays.stream(files).forEach(OkaeriConfig::load);
    }
}
