package com.qualityplus.bank.api.config;

import com.qualityplus.assistant.api.config.ConfigReloader;

public interface ConfigFiles<C, I, M, CMD, BU> extends ConfigReloader {
    C config();
    I inventories();
    M messages();
    CMD commands();
    BU bankUpgrades();
}
