package com.qualityplus.skills.base.perk.perks;

import com.qualityplus.assistant.util.random.RandomUtil;
import com.qualityplus.skills.base.perk.perks.common.AbstractPotionPerk;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.Optional;

/**
 * Utility class for potion master perk
 */
@NoArgsConstructor
public final class PotionMasterPerk extends AbstractPotionPerk {
    /**
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
    @Builder
    public PotionMasterPerk(final String id,
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
     * @param e {@link PlayerItemConsumeEvent}
     */
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void handlePerk(final PlayerItemConsumeEvent e) {
        if (!e.getItem().getType().equals(Material.POTION)) {
            return;
        }

        final Player player = e.getPlayer();

        if (RandomUtil.randomBetween(0.0, 100.0) >= getChancePerLevel() * getStat(player)) {
            return;
        }

        final PotionEffect effect = getPotion(getStat(player), e.getItem());

        if (effect == null) {
            return;
        }

        player.addPotionEffect(effect);
    }

    protected PotionEffect getPotion(final int level, final ItemStack item) {
        if (!(item.getItemMeta() instanceof PotionMeta)) {
            return null;
        }

        final PotionEffectType type = Optional.ofNullable((PotionMeta) item.getItemMeta())
                .map(meta -> meta.getBasePotionData().getType().getEffectType()).
                orElse(null);

        return Optional.ofNullable(type).map(t -> new PotionEffect(type, getDurationTicks(level), getLevel())).orElse(null);
    }
}
