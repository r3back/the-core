package com.qualityplus.alchemist;

import com.qualityplus.alchemist.api.VoxAlchemyAPI;
import com.qualityplus.alchemist.api.recipes.Recipes;
import com.qualityplus.alchemist.base.config.RecipesFile;
import com.qualityplus.assistant.lib.eu.okaeri.injector.annotation.Inject;
import com.qualityplus.assistant.okaeri.OkaeriSilentPlugin;

import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Register;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Scan;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.plan.ExecutionPhase;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.plan.Planned;
import lombok.Getter;

/**
 * Main class
 */
@Register(Recipes.class)
@Scan(deep = true)
public final class VoxAlchemy extends OkaeriSilentPlugin {
    private static @Inject @Getter VoxAlchemyAPI api;

    /**
     * Save all recipes when server shutdown
     *
     * @param recipesFile {@link RecipesFile}
     */
    @Planned(ExecutionPhase.PRE_SHUTDOWN)
    private void saveOnShutdown(@Inject final RecipesFile recipesFile) {
        recipesFile.save();
    }
}
