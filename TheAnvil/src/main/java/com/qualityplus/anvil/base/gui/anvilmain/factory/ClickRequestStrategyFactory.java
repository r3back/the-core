package com.qualityplus.anvil.base.gui.anvilmain.factory;

import com.qualityplus.anvil.base.gui.anvilmain.request.ClickRequest;
import com.qualityplus.anvil.base.gui.anvilmain.strategy.ClickRequestStrategy;

import java.util.Optional;

public interface ClickRequestStrategyFactory {
    public Optional<ClickRequestStrategy> getStrategy(final ClickRequest request);
}
