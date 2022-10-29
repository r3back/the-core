package com.qualityplus.anvil;

import com.qualityplus.anvil.api.TheAnvilAPI;
import com.qualityplus.anvil.base.config.RecipesFile;
import com.qualityplus.assistant.okaeri.OkaeriSilentPlugin;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Scan;
import eu.okaeri.platform.core.plan.ExecutionPhase;
import eu.okaeri.platform.core.plan.Planned;
import lombok.Getter;

@Scan(deep = true)
public final class TheAnvil extends OkaeriSilentPlugin {
    private static @Inject @Getter TheAnvilAPI api;

    @Planned(ExecutionPhase.PRE_SHUTDOWN)
    private void saveOnShutdown(@Inject RecipesFile recipesFile) {
        recipesFile.saveRecipes();
    }
}
