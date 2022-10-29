package com.qualityplus.dragon.base.configs;

import com.qualityplus.dragon.api.game.guardian.Guardian;
import eu.okaeri.configs.OkaeriConfig;
import com.qualityplus.dragon.base.game.guardian.DragonGuardian;
import com.qualityplus.dragon.base.game.guardian.GuardianArmor;
import eu.okaeri.configs.annotation.Header;
import eu.okaeri.configs.annotation.NameModifier;
import eu.okaeri.configs.annotation.NameStrategy;
import eu.okaeri.configs.annotation.Names;
import eu.okaeri.platform.core.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration(path = "guardians.yml")
@Header("================================")
@Header("       Dragon Guardians      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class DragonGuardiansFile extends OkaeriConfig {
    public Map<String, DragonGuardian> guardianMap = new HashMap<>();

    public Guardian getGuardianById(String name){
        return guardianMap.getOrDefault(name, null);
    }

    public void addGuardian(String name){
        guardianMap.put(name, new DragonGuardian(name, new GuardianArmor()));
    }

    public void remove(String name){
        guardianMap.remove(name);
    }
}