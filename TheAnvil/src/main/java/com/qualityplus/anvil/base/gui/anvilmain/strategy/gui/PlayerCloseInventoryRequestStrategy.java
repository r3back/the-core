package com.qualityplus.anvil.base.gui.anvilmain.strategy.gui;

import com.qualityplus.anvil.base.gui.anvilmain.request.ClickRequest;
import com.qualityplus.anvil.base.gui.anvilmain.strategy.CancellableClickRequestStrategy;
import com.qualityplus.anvil.util.ClickLocation;
import com.qualityplus.anvil.util.ClickSlot;
import com.qualityplus.assistant.inventory.Item;
import org.bukkit.entity.HumanEntity;

public final class PlayerCloseInventoryRequestStrategy extends CancellableClickRequestStrategy {
    @Override
    public boolean applies(final ClickRequest request) {
        final Item closeItem = request.getConfig().getCloseGUI();

        return ClickLocation.of(request).isGuiInventory() && ClickSlot.isSlot(request.getSlot(), closeItem);
    }

    @Override
    public void execute(final ClickRequest request) {
        cancelEvent(request);

        request.getPlayer().ifPresent(HumanEntity::closeInventory);
    }
}
