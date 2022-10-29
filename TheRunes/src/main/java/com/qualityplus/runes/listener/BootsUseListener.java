package com.qualityplus.runes.listener;

import com.qualityplus.assistant.util.itemstack.ItemStackUtils;
import com.qualityplus.runes.api.provider.EffectProvider;
import com.qualityplus.runes.api.session.RuneInstance;
import com.qualityplus.runes.util.RunesUtils;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

@Component
public final class BootsUseListener implements Listener {
    private @Inject EffectProvider effectProvider;

    @EventHandler
    public void onKillWithSword(PlayerMoveEvent e) {
        Player player = e.getPlayer();

        ItemStack boots = player.getInventory().getBoots();

        if(ItemStackUtils.isNull(boots)) return;

        RuneInstance runeInstance = RunesUtils.getRuneItemInstance(boots);

        if(runeInstance.getRune() == null) return;

        effectProvider.execute(player, e.getPlayer(), runeInstance.getRune());
    }
}
