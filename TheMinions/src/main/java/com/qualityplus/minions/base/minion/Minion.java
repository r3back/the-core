package com.qualityplus.minions.base.minion;

import com.qualityplus.assistant.util.time.Timer;
import com.qualityplus.minions.base.minion.egg.MinionEgg;
import com.qualityplus.minions.base.minion.entity.MinionEntityOptions;
import com.qualityplus.minions.base.minion.layout.MinionLayout;
import com.qualityplus.minions.base.minion.level.MinionLevel;
import eu.okaeri.configs.OkaeriConfig;
import lombok.*;

import java.util.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public final class Minion extends OkaeriConfig {
    private String id;
    private String displayName;
    private MinionEgg minionEgg;
    private List<String> description;
    private MinionLayout minionLayout;
    private Map<Integer, MinionLevel> minionLevels;
    private MinionEntityOptions minionEntityOptions;


    public Timer getTimer(int level) {
        return getMinionLevel(level)
                .map(MinionLevel::getExecuteActionsTime)
                .orElse(null);
    }

    public Optional<MinionLevel> getMinionLevel(int level) {
        Map<Integer, MinionLevel> toWorkWith = minionLevels;

        if (toWorkWith.containsKey(level)) {
            return Optional.of(toWorkWith.get(level));
        } else {
            int highestLevel = 1;

            for (Integer startLevel : toWorkWith.keySet()) {
                if (startLevel > level)
                    break;

                if (startLevel > highestLevel)
                    highestLevel = startLevel;
            }

            return Optional.ofNullable(toWorkWith.getOrDefault(highestLevel, null));
        }
    }
}
