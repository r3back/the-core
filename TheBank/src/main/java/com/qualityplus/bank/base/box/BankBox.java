package com.qualityplus.bank.base.box;

import com.qualityplus.bank.api.box.Box;
import com.qualityplus.bank.api.config.ConfigFiles;
import com.qualityplus.bank.api.service.BankService;
import com.qualityplus.bank.base.config.*;
import com.qualityplus.bank.persistence.BankRepository;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Component;
import org.bukkit.plugin.Plugin;

@Component
public final class BankBox implements Box {
    private @Inject ConfigFiles<Config, Inventories, Messages, Commands, BankUpgrades> files;
    private @Inject BankRepository repository;
    private @Inject BankService bankService;
    private @Inject Plugin plugin;

    @Override
    public ConfigFiles<Config, Inventories, Messages, Commands, BankUpgrades> files() {
        return files;
    }

    @Override
    public BankRepository repository() {
        return repository;
    }

    @Override
    public BankService service() {
        return bankService;
    }

    @Override
    public Plugin plugin() {
        return plugin;
    }
}
