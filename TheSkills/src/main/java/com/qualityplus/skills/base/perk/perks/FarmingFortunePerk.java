package com.qualityplus.skills.base.perk.perks;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.skills.base.perk.perks.common.AbstractFortuneBlockPerk;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public final class FarmingFortunePerk extends AbstractFortuneBlockPerk {
    @Builder
    public FarmingFortunePerk(String id, boolean enabled, String displayName, List<String> description, GUIOptions skillGUIOptions, double initialAmount, double chancePerLevel,
                              List<XMaterial> allowedMaterials) {
        super(id, enabled, displayName, description, skillGUIOptions, initialAmount, chancePerLevel, allowedMaterials);
    }
}
