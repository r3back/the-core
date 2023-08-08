package com.qualityplus.skills.base.perk.perks;

import com.qualityplus.assistant.lib.com.cryptomorin.xseries.XMaterial;
import com.qualityplus.assistant.lib.com.cryptomorin.xseries.XPotion;
import com.qualityplus.assistant.util.random.RandomUtil;
import com.qualityplus.skills.base.perk.perks.common.AbstractPotionPerk;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.List;
import java.util.Optional;

/**
 * Utility class for iron lungs perk
 */
@NoArgsConstructor
public final class IronLungsPerk extends AbstractPotionPerk {
    /**
     * Makes a Iron lungs perk
     *
     * @param id                      Id
     * @param enabled                 Enabled
     * @param displayName             Display Name
     * @param description             Description
     * @param skillGUIOptions         {@link GUIOptions}
     * @param initialAmount           Initial Amount
     * @param chancePerLevel          Chance Per Level
     * @param secondsDurationPerLevel Seconds Duration Per level
     * @param baseSecondsDuration     Base Seconds Duration
     * @param level                   Level
     */
    @Builder
    public IronLungsPerk(final String id,
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
     * Adds a handle perk
     *
     * @param e {@link PlayerMoveEvent}
     */
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void handlePerk(final PlayerMoveEvent e) {
        final Player p = e.getPlayer();

        final Material m = e.getPlayer().getLocation().getBlock().getType();

        if (!m.equals(XMaterial.WATER.parseMaterial())) {
            return;
        }

        if (RandomUtil.randomBetween(0.0, 100.0) >= getChancePerLevel() * getStat(p)) {
            return;
        }

        Optional.of(XPotion.WATER_BREATHING)
                .map(potion -> potion.buildPotionEffect(getDurationTicks(getStat(p)), getLevel()))
                .ifPresent(p::addPotionEffect);
    }
}
