package com.qualityplus.minions.base.minions.minion.level;

import com.qualityplus.assistant.util.time.Timer;
import com.qualityplus.minions.base.minions.minion.recipes.MinionRecipeConfig;
import com.qualityplus.minions.base.minions.minion.skin.MinionSkin;
import eu.okaeri.configs.OkaeriConfig;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public final class MinionLevel extends OkaeriConfig {
    private MinionRecipeConfig minionRecipe;
    private MatRequirement matRequirement;
    private Timer executeActionsTime;
    private String minionSkin;
    private int maxStorage;
}
