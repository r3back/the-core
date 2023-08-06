package com.qualityplus.skills.base.perk.perks;

import com.qualityplus.assistant.base.event.PlayerDamagedByEntityEvent;
import com.qualityplus.assistant.lib.com.cryptomorin.xseries.XPotion;
import com.qualityplus.assistant.util.random.RandomUtil;
import com.qualityplus.skills.base.perk.perks.common.AbstractPotionPerk;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;

import java.util.List;
import java.util.Optional;

/**
 * Utility class Eagle eyes perk
 */
@NoArgsConstructor
public final class EagleEyesPerk extends AbstractPotionPerk {
    /**
     * Makes eagle eyes perk
     *
     * @param id                                 Id
     * @param enabled                            Enabled
     * @param displayName                        Display Name
     * @param description                        Description
     * @param skillGUIOptions                    {@link GUIOptions}
     * @param initialAmount                      Initial Amount
     * @param chancePerLevel                     Chance Per Level
     * @param secondsDurationPerLevel            Seconds Duration Per Level
     * @param baseSecondsDuration                Base Seconds Duration
     * @param level                              Level
     */
    @Builder
    public EagleEyesPerk(final String id,
                         final boolean enabled,
                         final String displayName,
                         final List<String> description,
                         final GUIOptions skillGUIOptions,
                         final double initialAmount,
                         final double chancePerLevel,
                         final int secondsDurationPerLevel,
                         final int baseSecondsDuration,
                         final int level) {
        super(id, enabled, displayName, description, skillGUIOptions, initialAmount, chancePerLevel, secondsDurationPerLevel, baseSecondsDuration, level);
    }

    /**
     * Adds an handle perk
     *
     * @param e {@link PlayerDamagedByEntityEvent}
     */
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void handlePerk(final PlayerDamagedByEntityEvent e) {
        final Player p = e.getPlayer();

        if (RandomUtil.randomBetween(0.0, 100.0) >= getChancePerLevel() * getStat(p)) {
            return;
        }
        Optional.of(XPotion.NIGHT_VISION)
                .map(potion -> potion.buildPotionEffect(getDurationTicks(getStat(p)), getLevel()))
                .ifPresent(p::addPotionEffect);
    }
}
