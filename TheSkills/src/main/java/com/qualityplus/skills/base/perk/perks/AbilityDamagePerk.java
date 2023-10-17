package com.qualityplus.skills.base.perk.perks;

import com.qualityplus.skills.base.perk.Perk;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Utility class for ability damage perk
 */
@Getter
@Setter
@NoArgsConstructor
public final class AbilityDamagePerk extends Perk {
    /**
     * Makes an ability damage perk
     *
     * @param id              Id
     * @param enabled         Enabled
     * @param displayName     Display Name
     * @param description     Description
     * @param skillGUIOptions {@link GUIOptions}
     * @param initialAmount   Initial Amount
     * @param chancePerLevel  Chance Per Level
     */
    @Builder
    public AbilityDamagePerk(final String id,
                             final boolean enabled,
                             final String displayName,
                             final List<String> description,
                             final GUIOptions skillGUIOptions,
                             final double initialAmount,
                             final double chancePerLevel) {
        super(id, enabled, displayName, description, skillGUIOptions, initialAmount, chancePerLevel);
    }
}
