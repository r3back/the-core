package com.qualityplus.assistant.base.nms;

import com.google.common.annotations.Beta;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.qualityplus.assistant.api.nms.NMS;
import com.qualityplus.assistant.base.event.ActionBarMessageEvent;
import eu.okaeri.commons.bukkit.UnsafeBukkitCommons;
import org.bukkit.block.Block;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import com.qualityplus.assistant.base.event.ActionBarMessageEvent.ActionBarType;

import java.util.*;
import java.util.concurrent.TimeUnit;

public abstract class AbstractNMS implements NMS {
    protected final Cache<Block, Integer> clickCache = CacheBuilder.newBuilder()
            .concurrencyLevel(4)
            .maximumSize(1000)
            .expireAfterWrite(10, TimeUnit.SECONDS)
            .build();
    private final Map<UUID, Long> disabled = new HashMap<>();
    private final Map<UUID, Long> enabled = new HashMap<>();
    protected static BossBar bossBar;


    @Override
    public void sendActionBar(Player player, String message){
        if (!player.isOnline())
            return;

        ActionBarType type = ActionBarType.ACTION_BAR_TEXT;

        try {
            UnsafeBukkitCommons.sendMessage(player, message, UnsafeBukkitCommons.ChatTarget.ACTION_BAR);
            Event event = new ActionBarMessageEvent(player, message, type);

            Bukkit.getPluginManager().callEvent(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendTitle(Player player, String title, String subtitle, int fadeIn, int stay, int fadeOut){
        UnsafeBukkitCommons.sendTitle(player, title, subtitle, fadeIn, stay, fadeOut);
    }


    public void blacklist(UUID uuid) {
        if (isWhitelisted(uuid))
            return;

        disabled.put(uuid, System.currentTimeMillis() + 3000);
    }

    public boolean isBlacklisted(UUID uuid) {
        if(!disabled.containsKey(uuid)) return false;
        long endTime = disabled.get(uuid);
        return endTime > System.currentTimeMillis();
    }

    public void whitelistTemp(UUID uuid) {
        enabled.put(uuid, System.currentTimeMillis() + 50);
    }

    public boolean isWhitelisted(UUID uuid) {
        if(!enabled.containsKey(uuid)) return false;
        long endTime = enabled.get(uuid);
        return endTime > System.currentTimeMillis();
    }



}
