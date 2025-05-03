package com.qualityplus.enchanting.listener;

import com.qualityplus.enchanting.base.event.VoxEnchantingEnchantEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public final class EcoSkillsInternalListener implements Listener {
    @EventHandler
    public void onPlayerEnchant(final VoxEnchantingEnchantEvent event) {
        final Player player = event.getPlayer();

    }
}
