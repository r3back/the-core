package com.qualityplus.trades;

import com.qualityplus.trades.api.TheTradesAPI;
import com.qualityplus.trades.api.recipes.Trades;
import com.qualityplus.trades.base.config.TradesFile;
import com.qualityplus.assistant.okaeri.OkaeriSilentPlugin;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Register;
import eu.okaeri.platform.core.annotation.Scan;
import eu.okaeri.platform.core.plan.ExecutionPhase;
import eu.okaeri.platform.core.plan.Planned;
import lombok.Getter;

@Register(Trades.class)
@Scan(deep = true)
public final class TheTrades extends OkaeriSilentPlugin {
    private static @Inject @Getter TheTradesAPI api;

    @Planned(ExecutionPhase.PRE_SHUTDOWN)
    private void saveOnShutdown(@Inject TradesFile recipesFile) {
        recipesFile.save();
    }
}
