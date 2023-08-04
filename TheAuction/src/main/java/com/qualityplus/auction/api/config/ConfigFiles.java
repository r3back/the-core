package com.qualityplus.auction.api.config;

import com.qualityplus.assistant.api.config.ConfigReloader;

/**
 * Makes a files
 *
 * @param <C>   Config
 * @param <I>   Inventories
 * @param <M>   Messages
 * @param <CMD> Commands
 * @param <BU>  Bank Upgrades
 */
public interface ConfigFiles<C, I, M, CMD, BU> extends ConfigReloader {

    /**
     * Adds config
     *
     * @return {@link ConfigFiles}
     */
    public C config();

    /**
     * Adds inventories
     *
     * @return {@link ConfigFiles}
     */
    public I inventories();

    /**
     * Adds messages
     *
     * @return {@link ConfigFiles}
     */
    public M messages();

    /**
     * Adds commands
     *
     * @return {@link ConfigFiles}
     */
    public CMD commands();

    /**
     * Adds bank upgrades
     *
     * @return {@link ConfigFiles}
     */
    public BU bankUpgrades();
}
