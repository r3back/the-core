package com.qualityplus.minions.base.minions.minion.recipes;

import eu.okaeri.configs.OkaeriConfig;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public final class MinionRecipeConfig extends OkaeriConfig {
    private boolean enabled;
    private String recipeId;
}
