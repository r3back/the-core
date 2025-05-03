package com.qualityplus.minions.base.config.impl;

import com.qualityplus.assistant.lib.eu.okaeri.injector.annotation.Inject;
import com.qualityplus.minions.api.config.ConfigFiles;
import com.qualityplus.minions.base.config.*;
import com.qualityplus.minions.base.config.minions.CowMinion;
import com.qualityplus.minions.base.config.minions.DiamondMinion;
import com.qualityplus.minions.base.config.minions.SnowMinion;
import com.qualityplus.minions.base.config.minions.WheatMinion;
import com.qualityplus.minions.base.config.upgrades.AutomatedShippingUpgrades;
import com.qualityplus.minions.base.config.upgrades.FuelUpgrades;
import com.qualityplus.minions.base.config.upgrades.NormalUpgrades;
import com.qualityplus.minions.base.minions.entity.type.CropBreakMinion;

import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Component;
import lombok.Getter;

@Component
public final class MinionFiles implements ConfigFiles<Config, Inventories, Messages, Commands, Skins, AutomatedShippingUpgrades, FuelUpgrades, NormalUpgrades> {
    private @Inject AutomatedShippingUpgrades autoSell;
    private @Inject NormalUpgrades normalUpgrades;
    private @Inject FuelUpgrades fuelUpgrades;
    private @Inject Inventories inventories;
    private @Inject Skins skins;
    private @Inject Messages messages;
    private @Inject Commands commands;
    private @Inject Config config;
    private @Inject DiamondMinion diamondMinion;
    private @Inject WheatMinion cropBreakMinion;
    private @Inject SnowMinion snowMinion;
    private @Inject CowMinion cowMinion;


    @Override
    public Config config() {
        return config;
    }

    @Override
    public Skins skins() {
        return skins;
    }

    @Override
    public AutomatedShippingUpgrades getAutoSell() {
        return autoSell;
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
        diamondMinion.load();
        cropBreakMinion.load();
        snowMinion.load();
        cowMinion.load();
    }
}
