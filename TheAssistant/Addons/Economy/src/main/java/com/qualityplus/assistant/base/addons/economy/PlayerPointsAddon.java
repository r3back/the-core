package com.qualityplus.assistant.base.addons.economy;

import com.qualityplus.assistant.api.addons.EconomyAddon;
import org.black_ixx.playerpoints.PlayerPoints;
import org.bukkit.OfflinePlayer;

public final class PlayerPointsAddon implements EconomyAddon {
    @Override
    public double getMoney(OfflinePlayer player) {
        return PlayerPoints.getInstance().getAPI().look(player.getUniqueId());
    }

    @Override
    public void withdrawMoney(OfflinePlayer player, double amount) {
        PlayerPoints.getInstance().getAPI().take(player.getUniqueId(), (int) amount);
    }

    @Override
    public void depositMoney(OfflinePlayer player, double amount) {
        PlayerPoints.getInstance().getAPI().give(player.getUniqueId(), (int) amount);
    }

    @Override
    public String getAddonName() {
        return "Player Points";
    }
}
