package com.qualityplus.runes;

import com.qualityplus.runes.api.TheRunesAPI;
import com.qualityplus.runes.api.recipes.Runes;
import com.qualityplus.runes.base.config.RunesFile;
import com.qualityplus.assistant.okaeri.OkaeriSilentPlugin;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Register;
import eu.okaeri.platform.core.annotation.Scan;
import eu.okaeri.platform.core.plan.ExecutionPhase;
import eu.okaeri.platform.core.plan.Planned;
import lombok.Getter;

@Register(Runes.class)
@Scan(deep = true)
public final class TheRunes extends OkaeriSilentPlugin {
    private static @Inject @Getter TheRunesAPI api;

    @Planned(ExecutionPhase.PRE_SHUTDOWN)
    private void saveOnShutdown(@Inject RunesFile recipesFile) {
        recipesFile.save();
    }
}
