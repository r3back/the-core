package com.qualityplus.anvil.base.gui.anvilmain.strategy.gui;

import com.qualityplus.anvil.base.gui.anvilmain.request.ClickRequest;
import com.qualityplus.anvil.base.gui.anvilmain.strategy.CancellableClickRequestStrategy;
import com.qualityplus.anvil.util.ClickLocation;

public final class NormalClickInAnvilItemsStrategy extends CancellableClickRequestStrategy {
    @Override
    public boolean applies(final ClickRequest request) {
        return ClickLocation.of(request).isGuiInventory() &&
                !request.isPickUpSlot() &&
                !request.isClickToCombineSlot() &&
                !request.isSacrificeSlot() &&
                !request.isCloseSlot();
    }

    @Override
    public void execute(final ClickRequest request) {
        cancelEvent(request);
    }
}
