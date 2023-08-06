package com.qualityplus.skills.base.perk;

import com.qualityplus.assistant.api.util.MathUtil;
import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.assistant.util.number.NumberUtil;
import com.qualityplus.assistant.util.placeholder.Placeholder;
import com.qualityplus.assistant.util.placeholder.PlaceholderBuilder;
import com.qualityplus.skills.TheSkills;
import com.qualityplus.skills.api.effect.CommonObject;
import com.qualityplus.skills.api.listener.ExtraListener;
import com.qualityplus.skills.api.registry.ListenerRegistrable;
import com.qualityplus.skills.base.perk.registry.Perks;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import com.qualityplus.skills.persistance.data.UserData;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Utility class for perk
 */
@Getter
@Setter
@NoArgsConstructor
public abstract class Perk extends CommonObject implements ListenerRegistrable {
    protected double chancePerLevel;

    /**
     * Makes a perk
     *
     * @param id                                 Id
     * @param enabled                            Enabled
     * @param displayName                        Display Name
     * @param description                        Description
     * @param skillGUIOptions                    {@link GUIOptions}
     * @param initialAmount                      Initial Amount
     * @param chancePerLevel                     Chance Per Level
     */
    public Perk(final String id,
                final boolean enabled,
                final String displayName,
                final List<String> description,
                final GUIOptions skillGUIOptions,
                final double initialAmount,
                final double chancePerLevel) {
        super(id, enabled, displayName, description, skillGUIOptions, initialAmount);

        this.chancePerLevel = chancePerLevel;
    }

    /**
     * Adds a register
     */
    public void register() {
        Perks.registerNewPerk(this);
    }

    /**
     * Makes a player stat
     *
     * @param player {@link Player}
     * @param id     Id
     * @return       Stat
     */
    public int getStat(final Player player, final String id) {
        return TheSkills.getApi().getSkillsService().getData(player.getUniqueId())
                .map(UserData::getSkills)
                .map(userPerks -> userPerks.getLevel(id))
                .orElse(1);
    }

    /**
     * Add a stat
     *
     * @param player {@link Player}
     * @return       Stat
     */
    public int getStat(final Player player) {
        return getStat(player, id);
    }

    @Override
    public List<String> getFormattedDescription(final int level) {
        return StringUtils.processMulti(description, getPlaceholders(level).get());
    }

    @Override
    public void addExtraListener(final Class<? extends ExtraListener> listener) {
        extraListeners.add(listener);
    }

    protected PlaceholderBuilder getPlaceholders(final int level) {
        return PlaceholderBuilder.create()
                .with(new Placeholder("level_number", level), new Placeholder("level_roman", NumberUtil.toRoman(level)))
                .with(new Placeholder("percent", MathUtil.round(getChancePerLevel() * level)));
    }

}
