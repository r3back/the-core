package com.qualityplus.anvil.base.gui.anvilmain.strategy.gui.shift;

import com.qualityplus.anvil.base.gui.anvilmain.request.ClickRequest;
import com.qualityplus.anvil.base.gui.anvilmain.strategy.CancellableClickRequestStrategy;
import com.qualityplus.anvil.util.ClickLocation;

public final class ShiftClickToCombineRequestStrategy extends CancellableClickRequestStrategy {
    @Override
    public boolean applies(final ClickRequest request) {
        return ClickLocation.of(request).isGuiInventory() &&
                request.isClickToCombineSlot() &&
                request.isShiftClick();
    }

    @Override
    public void execute(final ClickRequest request) {
        cancelEvent(request);
    }
}
