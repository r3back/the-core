package com.qualityplus.runes.base.rune;

import eu.okaeri.configs.OkaeriConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public final class RuneLevel extends OkaeriConfig {
    private final int level;
    private final double successChance;
    private final int requiredRuneCraftingLevel;
}
