package com.qualityplus.bank.api.box;

import com.qualityplus.bank.api.config.ConfigFiles;
import com.qualityplus.bank.api.service.BankService;
import com.qualityplus.bank.base.config.*;
import com.qualityplus.bank.persistence.BankRepository;
import org.bukkit.plugin.Plugin;

public interface Box {
    ConfigFiles<Config, Inventories, Messages, Commands, BankUpgrades> files();
    BankRepository repository();
    BankService service();
    Plugin plugin();
}
