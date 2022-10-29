package com.qualityplus.assistant.permissionable;

import org.bukkit.entity.Player;

import java.util.Objects;

public interface Permissionable {
    String getPermission();

    default boolean hasPermission(Player player){
        return getPermission() == null || (!Objects.equals(getPermission(), "") && player.hasPermission(getPermission()));
    }
}
