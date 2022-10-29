package com.qualityplus.pets;

import com.qualityplus.assistant.okaeri.OkaeriSilentPlugin;
import com.qualityplus.pets.api.ThePetsAPI;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Scan;
import lombok.Getter;

@Scan(deep = true)
public final class ThePets extends OkaeriSilentPlugin {
    private static @Inject @Getter ThePetsAPI api;
}
