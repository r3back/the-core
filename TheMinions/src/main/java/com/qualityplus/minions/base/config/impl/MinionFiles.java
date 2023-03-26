package com.qualityplus.minions.base.config.impl;

import com.qualityplus.minions.api.config.ConfigFiles;
import com.qualityplus.minions.base.config.*;
import com.qualityplus.minions.base.config.upgrades.AutomatedShippingUpgrades;
import com.qualityplus.minions.base.config.upgrades.FuelUpgrades;
import com.qualityplus.minions.base.config.upgrades.NormalUpgrades;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Component;
import lombok.Getter;

@Component
@Getter
public final class MinionFiles implements ConfigFiles<Config, Inventories, Messages, Commands, Skins, AutomatedShippingUpgrades, FuelUpgrades, NormalUpgrades> {
    private @Inject AutomatedShippingUpgrades autoSell;
    private @Inject NormalUpgrades normalUpgrades;
    private @Inject FuelUpgrades fuelUpgrades;
    private @Inject Inventories inventories;
    private @Inject Messages messages;
    private @Inject Commands commands;
    private @Inject Config config;
    private @Inject Skins skins;

    @Override
    public Config config() {
        return config;
    }

    @Override
    public Skins souls() {
        return skins;
    }

    @Override
    public FuelUpgrades fuelUpgrades() {
        return fuelUpgrades;
    }

    @Override
    public NormalUpgrades upgrades() {
        return normalUpgrades;
    }

    @Override
    public Inventories inventories() {
        return inventories;
    }

    @Override
    public Messages messages() {
        return messages;
    }

    @Override
    public Commands commands() {
        return commands;
    }

    @Override
    public void reloadFiles() {
        normalUpgrades.load();
        autoSell.load();
        fuelUpgrades.load();
        config.load();
        skins.load();
        messages.load();
        inventories.load();
        commands.load();
    }
}
