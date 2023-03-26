package com.qualityplus.minions.api.config;

import com.qualityplus.assistant.api.config.ConfigReloader;

public interface ConfigFiles<C, I, M, CMD, S, AS, FU, U> extends ConfigReloader {
    C config();
    I inventories();
    M messages();
    CMD commands();
    S skins();
    AS getAutoSell();
    FU fuelUpgrades();
    U upgrades();
}
