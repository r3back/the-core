package com.qualityplus.skills.base.stat.stats;

import com.qualityplus.assistant.api.util.IPlaceholder;
import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.assistant.util.number.NumberUtil;
import com.qualityplus.assistant.util.placeholder.Placeholder;
import com.qualityplus.assistant.util.placeholder.PlaceholderBuilder;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import com.qualityplus.skills.base.stat.Stat;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Utility class for Strength stats
 */
@NoArgsConstructor
public final class StrengthStat extends Stat {
    /**
     * Make a strength stat
     *
     * @param id               Id
     * @param enabled          Enabled
     * @param displayName      Display Name
     * @param description      Description
     * @param skillGUIOptions  {@link GUIOptions}
     * @param baseAmount       Base Amount
     */
    @Builder
    public StrengthStat(final String id,
                        final boolean enabled,
                        final String displayName,
                        final List<String> description,
                        final GUIOptions skillGUIOptions,
                        final double baseAmount) {
        super(id, enabled, displayName, description, skillGUIOptions, baseAmount);
    }

    @Override
    public List<String> getFormattedDescription(final int level) {
        final List<IPlaceholder> placeholders = PlaceholderBuilder.create()
                .with(new Placeholder("level_number", level),
                      new Placeholder("level_roman", NumberUtil.toRoman(level)),
                      new Placeholder("chance", 0),
                      new Placeholder("damage_percentage", 0)

                ).get();
        return StringUtils.processMulti(description, placeholders);
    }
}
