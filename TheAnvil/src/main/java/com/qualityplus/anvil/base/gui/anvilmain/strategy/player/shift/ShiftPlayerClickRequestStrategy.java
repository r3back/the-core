package com.qualityplus.anvil.base.gui.anvilmain.strategy.player.shift;

import com.qualityplus.anvil.base.gui.anvilmain.request.ClickRequest;
import com.qualityplus.anvil.base.gui.anvilmain.strategy.ClickRequestStrategy;
import com.qualityplus.anvil.base.gui.anvilmain.strategy.player.AllowedItemsClickRequestChecker;
import com.qualityplus.anvil.util.ClickLocation;

public final class ShiftPlayerClickRequestStrategy extends AllowedItemsClickRequestChecker implements ClickRequestStrategy {
    @Override
    public boolean applies(final ClickRequest request) {
        return ClickLocation.of(request).isPlayerInventory() && request.isShiftClick();
    }

    @Override
    public void execute(final ClickRequest request) {
        check(request);
    }
}
