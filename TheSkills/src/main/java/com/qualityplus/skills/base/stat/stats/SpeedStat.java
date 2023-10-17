package com.qualityplus.skills.base.stat.stats;

import com.qualityplus.assistant.api.util.IPlaceholder;
import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.assistant.util.number.NumberUtil;
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
 * Utility class for speed stats
 */
@Data @EqualsAndHashCode(callSuper = true) @NoArgsConstructor
public final class SpeedStat extends Stat {
    private double extraSpeedPercentagePerLevel;

    /**
     * Makes a speed stats
     *
     * @param id                           Id
     * @param enabled                      Enabled
     * @param displayName                  Display Name
     * @param description                  Description
     * @param skillGUIOptions              {@link GUIOptions}
     * @param baseAmount                   Base Amount
     * @param extraSpeedPercentagePerLevel Extra Speed Percentage Per Level
     */
    @Builder
    public SpeedStat(final String id,
                     final boolean enabled,
                     final String displayName,
                     final List<String> description,
                     final GUIOptions skillGUIOptions,
                     final double baseAmount,
                     final double extraSpeedPercentagePerLevel) {
        super(id, enabled, displayName, description, skillGUIOptions, baseAmount);

        this.extraSpeedPercentagePerLevel = extraSpeedPercentagePerLevel;
    }

    @Override
    public List<String> getFormattedDescription(final int level) {
        final List<IPlaceholder> placeholders = PlaceholderBuilder.create()
                .with(new Placeholder("level_number", level),
                      new Placeholder("level_roman", NumberUtil.toRoman(level)),
                      new Placeholder("extra_speed", level * extraSpeedPercentagePerLevel)

                ).get();
        return StringUtils.processMulti(description, placeholders);
    }
}
