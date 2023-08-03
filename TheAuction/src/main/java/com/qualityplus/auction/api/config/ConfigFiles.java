package com.qualityplus.auction.api.config;

import com.qualityplus.assistant.api.config.ConfigReloader;

/**
 * Makes a files
 *
 * @param <C>   {@link ConfigFiles}
 * @param <I>   {@link ConfigFiles}
 * @param <M>   {@link ConfigFiles}
 * @param <CMD> {@link ConfigFiles}
 * @param <BU>  {@link ConfigFiles}
 */
public interface ConfigFiles<C, I, M, CMD, BU> extends ConfigReloader {

    /**
     * Adds config
     *
     * @return {@link ConfigFiles}
     */
    public C  config();

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
