package com.qualityplus.pets.api.config;

import com.qualityplus.assistant.api.config.ConfigReloader;

/**
 * Config files interface
 *
 * @param <C>   Config file
 * @param <I>   Inventories file
 * @param <M>   Messages file
 * @param <CMD> Commands file
 * @param <CAT> Categories file
 */
public interface ConfigFiles<C, I, M, CMD, CAT> extends ConfigReloader {
    C config();
    I inventories();
    M messages();
    CMD commands();
    CAT categories();
}
