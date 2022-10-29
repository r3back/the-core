package com.qualityplus.assistant.okaeri.commands;

import eu.okaeri.commands.bukkit.CommandsBukkit;
import eu.okaeri.commands.bukkit.listener.AsyncTabCompleteListener;
import eu.okaeri.commands.bukkit.listener.PlayerCommandSendListener;
import lombok.NonNull;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class SilentCommandsBukkit extends CommandsBukkit {
    private final JavaPlugin plugin;

    protected SilentCommandsBukkit(@NonNull JavaPlugin plugin) {
        super(plugin);
        this.plugin = plugin;
    }

    public static SilentCommandsBukkit make(JavaPlugin plugin){
        if (plugin == null) {
            throw new NullPointerException("plugin is marked non-null but is null");
        } else {
            SilentCommandsBukkit commandsBukkit = new SilentCommandsBukkit(plugin);
            commandsBukkit.registerListeners();
            return commandsBukkit;
        }
    }

    @Override
    public void registerListeners() {
        Class AsyncTabCompleteEvent;
        try {
            AsyncTabCompleteEvent = Class.forName("org.bukkit.event.player.PlayerCommandSendEvent");
            PlayerCommandSendListener playerCommandSendListener = new PlayerCommandSendListener(this, AsyncTabCompleteEvent);
            this.plugin.getServer().getPluginManager().registerEvent(AsyncTabCompleteEvent, new Listener() {
            }, EventPriority.HIGHEST, playerCommandSendListener, this.plugin, true);
        } catch (Exception var4) {
            //this.plugin.getLogger().warning("Failed to register PlayerCommandSendEvent listener: " + var4 + " (ignore if running an older version of Minecraft)");
        }

        try {
            AsyncTabCompleteEvent = Class.forName("com.destroystokyo.paper.event.server.AsyncTabCompleteEvent");
            AsyncTabCompleteListener asyncTabCompleteListener = new AsyncTabCompleteListener(this, AsyncTabCompleteEvent);
            this.plugin.getServer().getPluginManager().registerEvent(AsyncTabCompleteEvent, new Listener() {
            }, EventPriority.HIGHEST, asyncTabCompleteListener, this.plugin, true);
        } catch (Exception var3) {
            //this.plugin.getLogger().warning("Failed to register AsyncTabCompleteEvent listener: " + var3 + " (ignore if running an older version of Minecraft or not Paper)");
        }

    }
}
