package com.qualityplus.skills.base.perk.perks;

import com.qualityplus.assistant.api.util.IPlaceholder;
import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.assistant.util.number.NumberUtil;
import com.qualityplus.assistant.util.placeholder.Placeholder;
import com.qualityplus.assistant.util.placeholder.PlaceholderBuilder;
import com.qualityplus.skills.base.perk.Perk;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Utility class for bonus attack speed perk
 */
@Getter @Setter
@NoArgsConstructor
public final class BonusAttackSpeedPerk extends Perk {
    /**
     * Makes a bonus attack speed perk
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
    public BonusAttackSpeedPerk(final String id,
                                final boolean enabled,
                                final String displayName,
                                final List<String> description,
                                final GUIOptions skillGUIOptions,
                                final double initialAmount,
                                final double chancePerLevel) {
        super(id, enabled, displayName, description, skillGUIOptions, initialAmount, chancePerLevel);

    }

    /**
     * TODO ADD A SEPERATED LISTENER
     */

    @Override
    public List<String> getFormattedDescription(final int level) {
        final List<IPlaceholder> placeholders = PlaceholderBuilder.create()
                .with(new Placeholder("level_number", level), new Placeholder("level_roman", NumberUtil.toRoman(level)))
                .with(new Placeholder("percent", chancePerLevel * level))
                .get();
        return StringUtils.processMulti(description, placeholders);
    }
}
