package com.qualityplus.alchemist;

import com.qualityplus.alchemist.api.TheAlchemistAPI;
import com.qualityplus.alchemist.api.recipes.Recipes;
import com.qualityplus.alchemist.base.config.RecipesFile;
import com.qualityplus.assistant.okaeri.OkaeriSilentPlugin;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Register;
import eu.okaeri.platform.core.annotation.Scan;
import eu.okaeri.platform.core.plan.ExecutionPhase;
import eu.okaeri.platform.core.plan.Planned;
import lombok.Getter;

@Register(Recipes.class)
@Scan(deep = true)
public final class TheAlchemist extends OkaeriSilentPlugin {
    private static @Inject @Getter TheAlchemistAPI api;

    @Planned(ExecutionPhase.PRE_SHUTDOWN)
    private void saveOnShutdown(@Inject RecipesFile recipesFile) {
        recipesFile.save();
    }
}
