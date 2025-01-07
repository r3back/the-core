package com.qualityplus.minions.base.minions.entity.handler;

import com.qualityplus.minions.api.handler.*;

public interface MinionHandlers {
    public StorageHandler getStorageHandler();
    public AnimationHandler getAnimationHandler();
    public LayoutHandler getLayoutHandler();
    public SellHandler getSellHandler();
    public FuelHandler getFuelHandler();
}
