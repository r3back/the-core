package com.qualityplus.assistant.api.addons;

import com.qualityplus.assistant.api.dependency.DependencyPlugin;
import org.bukkit.OfflinePlayer;

public interface EconomyAddon extends DependencyPlugin {
    double getMoney(OfflinePlayer player);
    void depositMoney(OfflinePlayer player, double amount);
    void withdrawMoney(OfflinePlayer player, double amount);
}
