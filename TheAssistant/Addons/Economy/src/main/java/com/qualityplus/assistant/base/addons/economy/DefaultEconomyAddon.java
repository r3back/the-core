package com.qualityplus.assistant.base.addons.economy;

import com.qualityplus.assistant.api.addons.EconomyAddon;
import org.bukkit.OfflinePlayer;

public final class DefaultEconomyAddon implements EconomyAddon {
    @Override
    public double getMoney(OfflinePlayer player) {
        return 0;
    }

    @Override
    public void depositMoney(OfflinePlayer player, double amount) {

    }

    @Override
    public void withdrawMoney(OfflinePlayer player, double amount) {

    }

    @Override
    public String getAddonName() {
        return null;
    }
}
