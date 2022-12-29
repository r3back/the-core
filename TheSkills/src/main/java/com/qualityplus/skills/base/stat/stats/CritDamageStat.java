package com.qualityplus.skills.base.stat.stats;

import com.qualityplus.assistant.api.util.IPlaceholder;
import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.assistant.util.math.MathUtils;
import com.qualityplus.assistant.util.placeholder.Placeholder;
import com.qualityplus.assistant.util.placeholder.PlaceholderBuilder;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import com.qualityplus.skills.base.stat.Stat;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Extra damage when crit chance is made
 */
@Data @EqualsAndHashCode(callSuper = true) @NoArgsConstructor
public final class CritDamageStat extends Stat {
    private double damagePercentagePerLevel;
    private double morePercentageBase;

    @Builder
    public CritDamageStat(String id, boolean enabled, String displayName, List<String> description, GUIOptions skillGUIOptions, double baseAmount,
                          double damagePercentagePerLevel, double morePercentageBase) {
        super(id, enabled, displayName, description, skillGUIOptions, baseAmount);

        this.damagePercentagePerLevel = damagePercentagePerLevel;
        this.morePercentageBase = morePercentageBase;
    }

    @Override
    public List<String> getFormattedDescription(int level) {
        List<IPlaceholder> placeholders = PlaceholderBuilder.create()
                .with(new Placeholder("chance", damagePercentagePerLevel * level),
                        new Placeholder("level_number", level),
                        new Placeholder("level_roman", MathUtils.toRoman(level))
                ).get();
        return StringUtils.processMulti(description, placeholders);
    }
}
