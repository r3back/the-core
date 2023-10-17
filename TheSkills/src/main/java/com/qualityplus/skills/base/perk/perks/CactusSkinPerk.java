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
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;

import java.util.List;

/**
 * Utility class for cactus skin perk
 */
@Getter
@Setter
@NoArgsConstructor
public final class CactusSkinPerk extends Perk {
    private double damagePerLevel;
    private double damageBase;

    /**
     * Makes a cactus skin perk
     *
     * @param id              Id
     * @param enabled         Enabled
     * @param displayName     Display Name
     * @param description     Description
     * @param skillGUIOptions {@link GUIOptions}
     * @param initialAmount   Initial Amount
     * @param chancePerLevel  Chance Per Level
     * @param damagePerLevel  Damage Per Level
     * @param damageBase      Damage Base
     */
    @Builder
    public CactusSkinPerk(final String id,
                          final boolean enabled,
                          final String displayName,
                          final List<String> description,
                          final GUIOptions skillGUIOptions,
                          final double initialAmount,
                          final double chancePerLevel,
                          final double damagePerLevel,
                          final double damageBase) {
        super(id, enabled, displayName, description, skillGUIOptions, initialAmount, chancePerLevel);

        this.damagePerLevel = damagePerLevel;
        this.damageBase = damageBase;
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
        final double toDamage = damage(getStat(p));

        if (!(e.getEntity() instanceof LivingEntity)) {
            return;
        }

        ((LivingEntity) e.getEntity()).damage(toDamage);
    }

    private double damage(final int level) {
        return (this.damagePerLevel * level) + damageBase;
    }

    @Override
    public List<String> getFormattedDescription(final int level) {
        return StringUtils.processMulti(super.getFormattedDescription(level), PlaceholderBuilder.create(new Placeholder("damage", damage(level))).get());
    }
}
