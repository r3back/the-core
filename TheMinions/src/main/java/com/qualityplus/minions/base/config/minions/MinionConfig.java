package com.qualityplus.minions.base.config.minions;

import com.qualityplus.minions.base.minions.entity.MinionEntityOptions;
import com.qualityplus.minions.base.minions.minion.Minion;
import com.qualityplus.minions.base.minions.minion.MinionType;
import com.qualityplus.minions.base.minions.minion.egg.MinionEgg;
import com.qualityplus.minions.base.minions.minion.layout.LayoutGUISettings;
import com.qualityplus.minions.base.minions.minion.layout.MinionLayout;
import com.qualityplus.minions.base.minions.minion.level.MinionLevel;
import com.qualityplus.minions.base.minions.minion.update.MinionSettings;
import eu.okaeri.configs.OkaeriConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class MinionConfig extends OkaeriConfig {
    public String id;
    public String displayName;
    public MinionType type;
    public MinionEntityOptions minionEntityOptions;
    public MinionLayout minionLayout;
    public MinionEgg minionEgg;
    public Map<Integer, MinionLevel> minionLevels;
    public List<String> description;
    public LayoutGUISettings layoutGUISettings;
    public MinionSettings minionSettings;


    public Minion getMinion(){
        return Minion.builder()
                .id(id)
                .displayName(displayName)
                .type(type)
                .minionEntityOptions(minionEntityOptions)
                .minionLayout(minionLayout)
                .minionEgg(minionEgg)
                .minionLevels(minionLevels)
                .minionUpdateSettings(minionSettings)
                .description(description)
                .layoutGUISettings(layoutGUISettings)
                .build();
    }

}
