package com.qualityplus.skills.base.perk.perks;

import com.qualityplus.assistant.base.event.EntityDamagedByPlayerEvent;
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
 * Utility class for lightning punch perk
 */
@Getter
@Setter
@NoArgsConstructor
public final class LightningPunchPerk extends Perk {
    private double damage;

    /**
     *
     * @param id              Id
     * @param enabled         Enabled
     * @param displayName     Display Name
     * @param description     Description
     * @param skillGUIOptions {@link GUIOptions}
     * @param initialAmount   Initial Amount
     * @param chancePerLevel  Chance Per Level
     * @param damage          Damage
     */
    @Builder
    public LightningPunchPerk(final String id,
                              final boolean enabled,
                              final String displayName,
                              final List<String> description,
                              final GUIOptions skillGUIOptions,
                              final double initialAmount,
                              final double chancePerLevel,
                              final double damage) {
        super(id, enabled, displayName, description, skillGUIOptions, initialAmount, chancePerLevel);

        this.damage = damage;
    }

    /**
     * Adds a handle perk
     *
     * @param e {@link EntityDamagedByPlayerEvent}
     */
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void handlePerk(final EntityDamagedByPlayerEvent e) {
        final Player p = e.getPlayer();

        if (!(e.getEntity() instanceof LivingEntity)) {
            return;
        }

        final LivingEntity entity = (LivingEntity) e.getEntity();

        if (RandomUtil.randomBetween(0.0, 100.0) >= getChancePerLevel() * getStat(p)) {
            return;
        }
        entity.getWorld().strikeLightningEffect(entity.getLocation());

        entity.damage(this.damage);
    }

    @Override
    public List<String> getFormattedDescription(final int level) {
        return StringUtils.processMulti(super.getFormattedDescription(level), PlaceholderBuilder.create(new Placeholder("damage", this.damage)).get());
    }
}
