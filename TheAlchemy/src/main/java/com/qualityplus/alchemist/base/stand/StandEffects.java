package com.qualityplus.alchemist.base.stand;

import eu.okaeri.configs.OkaeriConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public final class StandEffects extends OkaeriConfig {
    private Map<Integer, List<StandEffect>> effectList;
}
