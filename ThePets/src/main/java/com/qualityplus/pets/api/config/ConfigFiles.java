package com.qualityplus.pets.api.config;

import com.qualityplus.assistant.api.config.ConfigReloader;

public interface ConfigFiles<C, I, M, CMD> extends ConfigReloader {
    C config();
    I inventories();
    M messages();
    CMD commands();
}
