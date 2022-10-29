package com.qualityplus.assistant.payable;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.assistant.util.inventory.InventoryUtils;
import org.bukkit.entity.Player;

import java.util.Map;

public interface ItemPayable {
    Map<XMaterial, Integer> getItemCost();

    default boolean canPayItems(Player player) {
        for (XMaterial material : getItemCost().keySet()) {
            int amount = getItemCost().get(material);

            int playerAmount = InventoryUtils.getItemQuantity(player.getInventory().getContents(), material.parseItem());

            if (amount > playerAmount) return false;
        }
        return true;
    }

    default void payItems(Player player) {
        getItemCost().keySet().forEach(material -> InventoryUtils.removeItems(player.getInventory(), material.parseItem(), getItemCost().get(material)));
    }
}
