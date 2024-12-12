package com.qualityplus.anvil.base.gui.anvilmain.strategy.gui;

import com.qualityplus.anvil.base.gui.anvilmain.request.ClickRequest;
import com.qualityplus.anvil.base.gui.anvilmain.strategy.CancellableClickRequestStrategy;
import com.qualityplus.anvil.util.ClickLocation;
import com.qualityplus.anvil.util.ClickSlot;
import com.qualityplus.assistant.inventory.Item;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;

public final class PlayerCloseInventoryRequestStrategy extends CancellableClickRequestStrategy {
    @Override
    public boolean applies(final ClickRequest request) {
        return ClickLocation.of(request).isGuiInventory() && request.isCloseSlot();
    }

    @Override
    public void execute(final ClickRequest request) {
        cancelEvent(request);

        Bukkit.getScheduler().runTaskLater(
                request.getBox().plugin(),
                () -> request.getPlayer().ifPresent(HumanEntity::closeInventory),
                3
        );
    }
}
