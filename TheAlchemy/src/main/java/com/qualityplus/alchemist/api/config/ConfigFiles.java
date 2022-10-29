package com.qualityplus.alchemist.api.config;

import com.qualityplus.assistant.api.config.ConfigReloader;

public interface ConfigFiles<C, R, I, M, CMD> extends ConfigReloader {
    C config();
    R recipes();
    I inventories();
    M messages();
    CMD commands();
}
