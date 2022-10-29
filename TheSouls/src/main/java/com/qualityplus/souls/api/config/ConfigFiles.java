package com.qualityplus.souls.api.config;

import com.qualityplus.assistant.api.config.ConfigReloader;

public interface ConfigFiles<C, I, M, CMD, T, S> extends ConfigReloader {
    C config();
    I inventories();
    M messages();
    CMD commands();
    T tiaTheFairy();
    S souls();
}
