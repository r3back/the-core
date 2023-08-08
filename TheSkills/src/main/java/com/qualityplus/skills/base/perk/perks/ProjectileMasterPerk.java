package com.qualityplus.skills.base.perk.perks;

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
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityShootBowEvent;

import java.util.List;

/**
 * Utility class for projectile master perk
 */
@Getter
@Setter
@NoArgsConstructor
public final class ProjectileMasterPerk extends Perk {
    private double extraDamagePercentageBasePerLevel;
    private double extraDamagePercentageBase;

    /**
     *
     * @param id                                Id
     * @param enabled                           Enabled
     * @param displayName                       Display Name
     * @param description                       Description
     * @param skillGUIOptions                   {@link GUIOptions}
     * @param initialAmount                     Initial Amount
     * @param chancePerLevel                    Chance Per Level
     * @param extraDamagePercentageBase         EXtra Damage Percentage Base
     * @param extraDamagePercentageBasePerLevel Extra Damage P1ercentage Base Per Level
     */
    @Builder
    public ProjectileMasterPerk(final String id,
                                final boolean enabled,
                                final String displayName,
                                final List<String> description,
                                final GUIOptions skillGUIOptions,
                                final double initialAmount,
                                final double chancePerLevel,
                                final double extraDamagePercentageBase,
                                final double extraDamagePercentageBasePerLevel) {
        super(id, enabled, displayName, description, skillGUIOptions, initialAmount, chancePerLevel);

        this.extraDamagePercentageBasePerLevel = extraDamagePercentageBasePerLevel;
        this.extraDamagePercentageBase = extraDamagePercentageBase;
    }

    /**
     * Adds a handle perk
     *
     * @param e {@link EntityShootBowEvent}
     */
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void handlePerk(final EntityShootBowEvent e) {
        final Projectile pr = (Projectile) e.getProjectile();

        if (!(pr.getShooter() instanceof Player)) {
            return;
        }

        final Player p = (Player) pr.getShooter();

        if (RandomUtil.randomBetween(0.0, 100.0) >= getChancePerLevel() * getStat(p)) {
            return;
        }
        final double damage = e.getEntity().getLastDamage();

        final double toDamage = (getPercentage() * damage) / 100;

        e.getEntity().damage(toDamage);
    }

    @Override
    public List<String> getFormattedDescription(final int level) {
        return StringUtils.processMulti(super.getFormattedDescription(level), PlaceholderBuilder
                .create(new Placeholder("projectile_percent", getPercentage())).get());
    }

    private double getPercentage() {
        return this.extraDamagePercentageBasePerLevel + extraDamagePercentageBase;
    }
}
