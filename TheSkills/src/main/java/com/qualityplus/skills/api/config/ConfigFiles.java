package com.qualityplus.skills.api.config;

import com.qualityplus.assistant.api.config.ConfigReloader;

/**
 * Makes a config files
 *
 * @param <T> Config
 * @param <I> Inventories
 * @param <C> Commands
 * @param <M> Messages
 */
public interface ConfigFiles <T, I, C, M> extends ConfigReloader {
    /**
     * Makes an T
     *
     * @return Config
     */
    public T config();

    /**
     * Makes an I
     *
     * @return Inventories
     */
    public I inventories();

    /**
     * Makes an M
     *
     * @return Messages
     */
    public M messages();

    /**
     * Makes an C
     *
     * @return Commands
     */
    public C commands();
}
