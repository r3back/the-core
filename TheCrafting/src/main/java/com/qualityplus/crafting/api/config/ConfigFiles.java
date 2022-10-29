package com.qualityplus.crafting.api.config;

import com.qualityplus.assistant.api.config.ConfigReloader;

public interface ConfigFiles<C, R, I, M, CMD, CA> extends ConfigReloader {
    C config();
    R recipes();
    I inventories();
    M messages();
    CMD commands();
    CA categories();
}
