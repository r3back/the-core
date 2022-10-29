package com.qualityplus.runes.base.table.effects;

import eu.okaeri.configs.OkaeriConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public final class RuneTableEffects extends OkaeriConfig {
    private Map<Integer, List<RuneTableEffect>> effectList;
}
