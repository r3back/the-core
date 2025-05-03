package com.qualityplus.runes.api.config;

import com.qualityplus.assistant.api.config.ConfigReloader;

public interface ConfigFiles<C, T, I, M, CMD> extends ConfigReloader {
    C config();
    T trades();
    I inventories();
    M messages();
    CMD commands();
}
