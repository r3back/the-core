package com.qualityplus.assistant.base.addons.economy;

import com.qualityplus.assistant.api.addons.EconomyAddon;
import me.realized.tokenmanager.api.TokenManager;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Optional;

public final class TokenManagerAddon implements EconomyAddon {
    private final TokenManager tokenManager = (TokenManager) Bukkit.getServer().getPluginManager().getPlugin("TokenManager");

    @Override
    public double getMoney(OfflinePlayer player) {
        return Optional.ofNullable(tokenManager).map(tm -> tm.getTokens((Player) player).orElse(0L)).orElse(0L);
    }

    @Override
    public void withdrawMoney(OfflinePlayer player, double amount) {
        Optional.ofNullable(tokenManager).ifPresent(tm -> tm.removeTokens(player.getName(), (long) amount));
    }

    @Override
    public void depositMoney(OfflinePlayer player, double amount) {
        Optional.ofNullable(tokenManager).ifPresent(tm -> tm.addTokens(player.getName(), (long) amount));
    }

    @Override
    public String getAddonName() {
        return "TokenManager";
    }
}
