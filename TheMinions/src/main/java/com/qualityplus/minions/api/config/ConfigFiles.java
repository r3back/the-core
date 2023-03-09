package com.qualityplus.minions.api.config;

import com.qualityplus.assistant.api.config.ConfigReloader;
import com.qualityplus.minions.base.config.minions.CowMinion;
import com.qualityplus.minions.base.config.minions.DiamondMinion;
import com.qualityplus.minions.base.config.minions.SnowMinion;
import com.qualityplus.minions.base.minions.entity.type.CropBreakMinion;

public interface ConfigFiles<C, I, M, CMD, S, AS, FU, U> extends ConfigReloader {
    CowMinion cowMinion();

    SnowMinion snowMinion();

    CropBreakMinion cropBreakMinion();

    DiamondMinion diamondMinion();

    C config();
    I inventories();
    M messages();
    CMD commands();
    S souls();
    AS getAutoSell();
    FU fuelUpgrades();
    U upgrades();
}
