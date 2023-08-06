package com.qualityplus.skills.api.config;

import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;

import java.util.List;

/**
 * Utility interface for all
 */
public interface AllGetter {
    /**
     * makes all config
     *
     * @return List Of {@link OkaeriConfig}
     */
    public List<OkaeriConfig> getAll();
}
