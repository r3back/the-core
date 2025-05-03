package com.qualityplus.auction;

import com.qualityplus.assistant.lib.eu.okaeri.injector.annotation.Inject;
import com.qualityplus.assistant.okaeri.OkaeriSilentPlugin;
import com.qualityplus.auction.api.VoxAuctionsAPI;
import com.qualityplus.auction.api.box.Box;
import com.qualityplus.auction.base.gui.AuctionGUI;
import com.qualityplus.auction.persistence.data.AuctionHouse;
import com.qualityplus.auction.persistence.data.User;

import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Scan;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.plan.ExecutionPhase;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.plan.Planned;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryHolder;


/**
 * Makes an auction
 */
@Scan(deep = true)
public final class VoxAuctions extends OkaeriSilentPlugin {
    private static @Inject @Getter VoxAuctionsAPI api;

    @Planned(ExecutionPhase.POST_STARTUP)
    private void whenStart() {
        Bukkit.getScheduler().runTaskTimer(this, () -> Bukkit.getServer().getOnlinePlayers().
                        forEach(player -> {
                            final InventoryHolder inventoryHolder = player.getOpenInventory()
                                    .getTopInventory()
                                    .getHolder();
                            if (inventoryHolder instanceof AuctionGUI) {
                                ((AuctionGUI) inventoryHolder).addContent();
                            }
                        }), 0, 20);
    }

    @Planned(ExecutionPhase.PRE_SHUTDOWN)
    private void whenStop(final Box box) {
        box.auctionService().getAuctionHouse().ifPresent(AuctionHouse::save);
        Bukkit.getOnlinePlayers()
                .stream()
                .map(Player::getUniqueId)
                .forEach(uuid -> box.service().getUser(uuid).ifPresent(User::save));
    }

}
