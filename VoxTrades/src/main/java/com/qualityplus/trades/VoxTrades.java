package com.qualityplus.trades;

import com.qualityplus.assistant.lib.eu.okaeri.injector.annotation.Inject;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Register;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Scan;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.plan.ExecutionPhase;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.plan.Planned;
import com.qualityplus.assistant.okaeri.OkaeriSilentPlugin;
import com.qualityplus.trades.api.TheTradesAPI;
import com.qualityplus.trades.api.recipes.Trades;
import com.qualityplus.trades.base.config.TradesFile;
import lombok.Getter;

@Register(Trades.class)
@Scan(deep = true)
public final class VoxTrades extends OkaeriSilentPlugin {
    private static @Inject @Getter TheTradesAPI api;

    @Planned(ExecutionPhase.PRE_SHUTDOWN)
    private void saveOnShutdown(@Inject final TradesFile recipesFile) {
        recipesFile.save();
    }
}
