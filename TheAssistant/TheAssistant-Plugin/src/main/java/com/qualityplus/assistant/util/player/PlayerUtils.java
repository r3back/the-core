package com.qualityplus.assistant.util.player;

import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@UtilityClass
public class PlayerUtils {
    public boolean isPlacedByPlayer(Block block){
        return block.hasMetadata("theAssistantPlayerBlock");
    }

    public static String getPlayerName(UUID uuid){
        if(uuid == null) return "";
        return Optional.ofNullable(Bukkit.getOfflinePlayer(uuid)).map(OfflinePlayer::getName).orElse("");
    }

    public List<String> parseWithName(List<String> list, Player player){
        return list.stream().map(line -> line.replace("%player%", player.getName())).collect(Collectors.toList());
    }
}
