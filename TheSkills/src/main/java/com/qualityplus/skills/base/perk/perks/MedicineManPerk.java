package com.qualityplus.skills.base.perk.perks;

import com.qualityplus.assistant.base.event.PlayerDamagedByEntityEvent;
import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.assistant.util.placeholder.Placeholder;
import com.qualityplus.assistant.util.placeholder.PlaceholderBuilder;
import com.qualityplus.assistant.util.random.RandomUtil;
import com.qualityplus.skills.base.perk.Perk;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;

import java.util.List;

/**
 * Utility class for medicine man perk
 */
@Getter
@Setter
@NoArgsConstructor
public final class MedicineManPerk extends Perk {
    private double healPercentageToRegeneratePerLevel;
    private double healPercentageToRegenerateBase;

    /**
     *
     * @param id                                 Id
     * @param enabled                            Enabled
     * @param displayName                        Display Name
     * @param description                        Description
     * @param skillGUIOptions                    {@link GUIOptions}
     * @param initialAmount                      Initial Amount
     * @param chancePerLevel                     Chance Per Level
     * @param healPercentageToRegenerateBase     Heal Percentage To Regenerate Base
     * @param healPercentageToRegeneratePerLevel Heal Percentage To Regenerate Base Level
     */
    @Builder
    public MedicineManPerk(final String id,
                           final boolean enabled,
                           final String displayName,
                           final List<String> description,
                           final GUIOptions skillGUIOptions,
                           final double initialAmount,
                           final double chancePerLevel,
                           final double healPercentageToRegenerateBase,
                           final double healPercentageToRegeneratePerLevel) {
        super(id, enabled, displayName, description, skillGUIOptions, initialAmount, chancePerLevel);

        this.healPercentageToRegeneratePerLevel = healPercentageToRegeneratePerLevel;
        this.healPercentageToRegenerateBase = healPercentageToRegenerateBase;
    }

    /**
     * Adds handle perk
     *
     * @param e {@link PlayerDamagedByEntityEvent}
     */
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void handlePerk(final PlayerDamagedByEntityEvent e) {
        final Player p = e.getPlayer();

        if (RandomUtil.randomBetween(0.0, 100.0) >= getChancePerLevel() * getStat(p)) {
            return;
        }
        final double toGive = (getPercentage() * p.getHealth()) * 100;

        p.setHealth(Math.min(p.getHealth() + toGive, p.getMaxHealth()));
    }

    @Override
    public List<String> getFormattedDescription(final int level) {
        return StringUtils.processMulti(super.getFormattedDescription(level), PlaceholderBuilder
                .create(new Placeholder("regen_percent", getPercentage())).get());
    }

    private double getPercentage() {
        return this.healPercentageToRegenerateBase + healPercentageToRegeneratePerLevel;
    }
}
