package com.qualityplus.alchemist.api.config;

import com.qualityplus.assistant.api.config.ConfigReloader;

/**
 * Config Files Handler
 *
 * @param <C>   Generic Config
 * @param <R>   Generic Recipes
 * @param <I>   Generic Inventories
 * @param <M>   Generic Messages
 * @param <CMD> Generic Commands
 */
public interface ConfigFiles<C, R, I, M, CMD> extends ConfigReloader {
    /**
     *
     * @return Config file implementation
     */
    public C config();

    /**
     *
     * @return Recipes file implementation
     */
    public R recipes();

    /**
     *
     * @return Inventories file implementation
     */
    public I inventories();

    /**
     *
     * @return Messages file implementation
     */
    public M messages();

    /**
     *
     * @return Commands file implementation
     */
    public CMD commands();
}
