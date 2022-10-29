package com.qualityplus.assistant.base.nms;

import com.qualityplus.assistant.api.nms.NMS;
import com.qualityplus.assistant.base.event.ActionBarMessageEvent;
import eu.okaeri.commons.bukkit.UnsafeBukkitCommons;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import com.qualityplus.assistant.base.event.ActionBarMessageEvent.ActionBarType;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

public abstract class AbstractNMS implements NMS {
    protected static BossBar bossBar;
    private final Map<UUID, Long> disabled = new HashMap<>();
    private final Map<UUID, Long> enabled = new HashMap<>();

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
