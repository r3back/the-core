package com.qualityplus.minions.base.gui.main.handler.click;

import com.qualityplus.minions.api.box.Box;
import com.qualityplus.minions.api.minion.MinionEntity;
import com.qualityplus.minions.base.event.PlayerPickUpMinionItemsEvent;
import com.qualityplus.minions.base.gui.main.handler.ClickHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class PickUpItemsClickHandler implements ClickHandler {
    @Override
    public void handle(InventoryClickEvent event, MinionEntity minionEntity, Box box) {
        final Player player = (Player) event.getWhoClicked();

        final List<ItemStack> itemStacks = new ArrayList<>();

        minionEntity.pickUpAllItems()
                .stream()
                .filter(Objects::nonNull)
                .forEach(item -> {
                    itemStacks.add(item.clone());
                    player.getInventory().addItem(item.clone());
                });

        Event pickUpEvent = new PlayerPickUpMinionItemsEvent(player, minionEntity, itemStacks);

        Bukkit.getServer().getPluginManager().callEvent(pickUpEvent);
    }
}
