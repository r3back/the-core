package com.qualityplus.skills.base.perk.perks;

import com.qualityplus.assistant.lib.com.cryptomorin.xseries.XMaterial;
import com.qualityplus.skills.base.perk.perks.common.AbstractFortuneBlockPerk;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Utility class for foraging fortune perk
 */
@NoArgsConstructor
public final class ForagingFortunePerk extends AbstractFortuneBlockPerk {
    /**
     * Makes a fortune perk
     *
     * @param id               Id
     * @param enabled          Enabled
     * @param displayName      Display Name
     * @param description      Description
     * @param skillGUIOptions  {@link GUIOptions}
     * @param initialAmount    Initial Amount
     * @param chancePerLevel   Chance Per Level
     * @param allowedMaterials Allowed Materials
     */
    @Builder
    public ForagingFortunePerk(final String id,
                               final boolean enabled,
                               final String displayName,
                               final List<String> description,
                               final GUIOptions skillGUIOptions,
                               final double initialAmount,
                               final double chancePerLevel,
                               final List<XMaterial> allowedMaterials) {
        super(id, enabled, displayName, description, skillGUIOptions, initialAmount, chancePerLevel, allowedMaterials);
    }
}
