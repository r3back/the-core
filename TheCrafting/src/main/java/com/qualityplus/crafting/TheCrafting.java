package com.qualityplus.crafting;

import com.qualityplus.crafting.api.TheCraftingAPI;
import com.qualityplus.crafting.base.config.RecipesFile;
import com.qualityplus.assistant.okaeri.OkaeriSilentPlugin;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Scan;
import eu.okaeri.platform.core.plan.ExecutionPhase;
import eu.okaeri.platform.core.plan.Planned;
import lombok.Getter;

@Scan(deep = true)
public final class TheCrafting extends OkaeriSilentPlugin {
    private static @Inject @Getter TheCraftingAPI api;

    @Planned(ExecutionPhase.PRE_SHUTDOWN)
    private void saveOnShutdown(@Inject RecipesFile recipesFile) {
        recipesFile.saveRecipes();
    }
}
