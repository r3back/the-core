package com.qualityplus.dragon.api.config;

import com.qualityplus.assistant.api.config.ConfigReloader;
import com.qualityplus.assistant.api.config.ConfigSaver;

public interface ConfigFiles<C, E, G, D, I, M, R, S, CMD> extends ConfigReloader, ConfigSaver {
    C config();
    E events();
    G guardians();
    D dragons();
    I inventories();
    M messages();
    R rewards();
    S structures();
    CMD commands();
}