package com.qualityplus.alchemist.base.stand;

import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * Stand animations
 */
@Getter
@Setter
@AllArgsConstructor
public final class StandEffects extends OkaeriConfig {
    private Map<Integer, List<StandEffect>> effectList;
}
