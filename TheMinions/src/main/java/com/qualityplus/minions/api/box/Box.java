package com.qualityplus.minions.api.box;

import com.qualityplus.minions.api.config.ConfigFiles;
import com.qualityplus.minions.api.service.MinionsService;
import com.qualityplus.minions.api.service.UserService;
import com.qualityplus.minions.base.config.*;
import com.qualityplus.minions.base.config.upgrades.AutomatedShippingUpgrades;
import com.qualityplus.minions.base.config.upgrades.FuelUpgrades;
import com.qualityplus.minions.base.config.upgrades.NormalUpgrades;
import org.bukkit.plugin.Plugin;

public interface Box {
    ConfigFiles<Config, Inventories, Messages, Commands, Skins, AutomatedShippingUpgrades, FuelUpgrades, NormalUpgrades> files();
    MinionsService getMinionsService();
    UserService getUserService();
    Plugin plugin();
}
