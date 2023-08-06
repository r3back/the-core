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
 * Utility class for intelligence stats
 */
@Data @EqualsAndHashCode(callSuper = true) @NoArgsConstructor
public final class IntelligenceStat extends Stat {
    /**
     * Makes a intelligence stats
     *
     * @param id              Id
     * @param enabled         Enabled
     * @param displayName     Display Name
     * @param description     Description
     * @param skillGUIOptions {@link GUIOptions}
     * @param baseAmount      Base Amount
     */
    @Builder
    public IntelligenceStat(final String id,
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
                .with(new Placeholder("intelligence", level),
                      new Placeholder("level_number", level),
                      new Placeholder("level_roman", NumberUtil.toRoman(level))
                ).get();
        return StringUtils.processMulti(description, placeholders);
    }
}
