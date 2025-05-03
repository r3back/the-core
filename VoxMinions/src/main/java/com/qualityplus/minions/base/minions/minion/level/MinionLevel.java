package com.qualityplus.minions.base.minions.minion.level;

import com.qualityplus.assistant.util.time.HumanTime;
import com.qualityplus.minions.base.minions.minion.recipes.MinionRecipeConfig;
import com.qualityplus.minions.base.minions.minion.skin.MinionSkin;
import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public final class MinionLevel extends OkaeriConfig {
    private MinionRecipeConfig minionRecipe;
    private MatRequirement matRequirement;
    private HumanTime executeActionsTime;
    private String minionSkin;
    private int maxStorage;
}
