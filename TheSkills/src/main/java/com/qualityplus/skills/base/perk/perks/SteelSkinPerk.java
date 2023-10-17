package com.qualityplus.skills.base.perk.perks;

import com.qualityplus.assistant.util.random.RandomUtil;
import com.qualityplus.skills.base.perk.Perk;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.List;

/**
 * Utility class for steel skin perk
 */
@NoArgsConstructor
public final class SteelSkinPerk extends Perk {
    /**
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
    public SteelSkinPerk(final String id,
                         final boolean enabled,
                         final String displayName,
                         final List<String> description,
                         final GUIOptions skillGUIOptions,
                         final double initialAmount,
                         final double chancePerLevel) {
        super(id, enabled, displayName, description, skillGUIOptions, initialAmount, chancePerLevel);
    }

    /**
     * Adds an entity damage
     *
     * @param event {@link EntityDamageEvent}
     */
    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void handle(final EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }

        final Player player = (Player) event.getEntity();

        if (event.getCause() != EntityDamageEvent.DamageCause.HOT_FLOOR && event.getCause() != EntityDamageEvent
                .DamageCause.LAVA && event.getCause() != EntityDamageEvent
                .DamageCause.FIRE_TICK && event.getCause() != EntityDamageEvent.DamageCause.FIRE) {
            return;
        }
        final double chance = chancePerLevel * getStat(player);

        if (RandomUtil.randomBetween(0.0, 100.0) < chance) {
            event.setCancelled(true);
        }
    }
}
