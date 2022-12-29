package com.qualityplus.minions.base.minions.entity.handler;

import com.qualityplus.minions.api.handler.*;

public interface MinionHandlers {
    StorageHandler getStorageHandler();
    AnimationHandler getAnimationHandler();
    LayoutHandler getLayoutHandler();
    SellHandler getSellHandler();
    FuelHandler getFuelHandler();
}
