package com.qualityplus.trades.api.provider;

import org.bukkit.entity.Player;

public interface CheckerProvider<T>{
    void checkout(Player player, T toCheckout);
}
