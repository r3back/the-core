package com.qualityplus.skills.base.perk.perks.common;

import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.assistant.util.number.NumberUtil;
import com.qualityplus.assistant.util.placeholder.Placeholder;
import com.qualityplus.assistant.util.placeholder.PlaceholderBuilder;
import com.qualityplus.skills.base.perk.Perk;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Utility class dor abstract option perk
 */
@Getter @Setter
@NoArgsConstructor
public abstract class AbstractPotionPerk extends Perk {
    protected int secondsDurationPerLevel;
    protected int baseSecondsDuration;
    protected int level;

    /**
     * Makes abstract potion perk
     *
     * @param id                      Id
     * @param enabled                 Enabled
     * @param displayName             Display Name
     * @param description             Description
     * @param skillGUIOptions         {@link GUIOptions}
     * @param initialAmount           Initial Amount
     * @param chancePerLevel          Chance Per Level
     * @param secondsDurationPerLevel Seconds Duration Per Level
     * @param baseSecondsDuration     Base Seconds Duration
     * @param level                   Level
     */
    public AbstractPotionPerk(final String id,
                              final boolean enabled,
                              final String displayName,
                              final List<String> description,
                              final GUIOptions skillGUIOptions,
                              final double initialAmount,
                              final double chancePerLevel,
                              final int secondsDurationPerLevel,
                              final int baseSecondsDuration,
                              final int level) {
        super(id, enabled, displayName, description, skillGUIOptions, initialAmount, chancePerLevel);

        this.secondsDurationPerLevel = secondsDurationPerLevel;
        this.baseSecondsDuration = baseSecondsDuration;
        this.level = level;
    }

    @Override
    public List<String> getFormattedDescription(final int level) {
        return StringUtils.processMulti(super.getFormattedDescription(level), PlaceholderBuilder.create(
                new Placeholder("duration", getDurationSeconds(level)),
                new Placeholder("potion_level_roman", NumberUtil.toRoman(getLevel())),
                new Placeholder("potion_level_number", getLevel())
        ).get());
    }

    protected int getFinalLevel() {
        return Math.max(0, this.level - 1);
    }

    protected int getDurationTicks(final int level) {
        return this.baseSecondsDuration + (this.secondsDurationPerLevel * level) * 20;
    }

    private int getDurationSeconds(final int level) {
        return this.baseSecondsDuration + (this.secondsDurationPerLevel * level);
    }
}
