package com.qualityplus.dragon.api.game.player;

import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public interface GamePlayer {
    Player getPlayer();
    UUID getUuid();
    String getName();
    double getDamage();
    void addDamage(double damage);
    void removeDamage(double damage);

    void sendMessage(String message);
    void sendMessage(List<String> message);

}
