package com.qualityplus.dragon.base.configs;

import com.qualityplus.assistant.util.faster.FastMap;
import com.qualityplus.dragon.base.game.dragon.TheDragonEntityImpl;
import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.Header;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.NameModifier;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.NameStrategy;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.Names;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Configuration;

import java.util.Map;

@Configuration(path = "dragons.yml")
@Header("================================")
@Header("       Dragons      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class DragonsFile extends OkaeriConfig {
    public Map<String, TheDragonEntityImpl> dragonMap = FastMap.builder(String.class, TheDragonEntityImpl.class)
            .put("ancient_dragon", new TheDragonEntityImpl("ancient_dragon", "Ancient Dragon", 500, 100, 10))
            .build();
}
