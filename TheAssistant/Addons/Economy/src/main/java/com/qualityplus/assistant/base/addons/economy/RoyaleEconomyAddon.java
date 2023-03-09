package com.qualityplus.assistant.base.addons.economy;

import com.qualityplus.assistant.api.addons.EconomyAddon;
import me.qKing12.RoyaleEconomy.RoyaleEconomy;
import org.bukkit.OfflinePlayer;

public final class RoyaleEconomyAddon implements EconomyAddon {

    @Override
    public double getMoney(OfflinePlayer player) {
        return RoyaleEconomy.apiHandler.balance.getBalance(player.getUniqueId().toString());
    }

    @Override
    public void withdrawMoney(OfflinePlayer player, double amount) {
        RoyaleEconomy.apiHandler.balance.removeBalance(player.getName(), amount);
    }

    @Override
    public void depositMoney(OfflinePlayer player, double amount) {
        RoyaleEconomy.apiHandler.balance.addBalance(player.getName(), amount);
    }

    @Override
    public String getAddonName() {
        return "Vault";
    }
}
