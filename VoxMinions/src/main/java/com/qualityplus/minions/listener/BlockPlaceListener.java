package com.qualityplus.minions.listener;

import com.qualityplus.assistant.api.util.BukkitItemUtil;
import com.qualityplus.assistant.lib.eu.okaeri.injector.annotation.Inject;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Component;
import com.qualityplus.minions.api.box.Box;
import com.qualityplus.minions.util.MinionEggUtil;
import com.qualityplus.minions.util.MinionUpgradeUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

@Component
public final class BlockPlaceListener implements Listener {
    private @Inject Box box;

    @EventHandler
    public void onJoin(final BlockPlaceEvent event) {
        final ItemStack itemStack = event.getItemInHand();

        if (BukkitItemUtil.isNull(itemStack)) {
            return;
        }

        if (MinionUpgradeUtil.isUpgrade(itemStack)) {
            event.setCancelled(true);
            return;
        }

        if (MinionUpgradeUtil.isFuel(itemStack)) {
            event.setCancelled(true);
            return;
        }

        if (MinionUpgradeUtil.isSkin(itemStack)) {
            event.setCancelled(true);
            return;
        }

        if (MinionUpgradeUtil.isAutoShip(itemStack)) {
            event.setCancelled(true);
            return;
        }

        if (MinionEggUtil.dataFromEgg(itemStack).isPresent()) {
            event.setCancelled(true);
        }
    }
}