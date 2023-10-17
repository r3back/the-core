package com.qualityplus.anvil.util;

import com.qualityplus.anvil.base.gui.anvilmain.request.ClickRequest;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.Objects;
import java.util.Optional;

public class ClickLocation {
    private final boolean isPlayerInventory;

    private ClickLocation(final boolean isPlayerInventory) {
        this.isPlayerInventory = isPlayerInventory;
    }

    public static ClickLocation of(final InventoryClickEvent e) {
        final Inventory clicked = e.getClickedInventory();
        final Inventory inventory = e.getInventory();

        final boolean isPlayerInventory = Optional.ofNullable(clicked)
                .filter(Objects::nonNull)
                .map(inv -> !inv.equals(inventory))
                .orElse(false);

        return new ClickLocation(isPlayerInventory);
    }

    public static ClickLocation of(final ClickRequest request) {
        return of(request.getEvent());
    }

    public boolean isPlayerInventory() {
        return this.isPlayerInventory;
    }

    public boolean isGuiInventory() {
        return !isPlayerInventory();
    }
}
