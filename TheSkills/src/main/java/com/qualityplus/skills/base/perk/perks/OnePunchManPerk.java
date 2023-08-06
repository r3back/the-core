package com.qualityplus.skills.base.perk.perks;

import com.qualityplus.assistant.base.event.EntityDamagedByPlayerEvent;
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
 * Utility class for one punch man perk
 */
@Getter
@Setter
@NoArgsConstructor
public final class OnePunchManPerk extends Perk {
    private boolean canBeUsedWithPlayers;

    /**
     *
     * @param id                    Id
     * @param enabled               Enabled
     * @param displayName           Display Name
     * @param description           Description
     * @param skillGUIOptions       {@link GUIOptions}
     * @param initialAmount         Initial Amount
     * @param chancePerLevel        Chance Per Level
     * @param canBeUsedWithPlayers  Can Be Used With Players
     */
    @Builder
    public OnePunchManPerk(final String id,
                           final boolean enabled,
                           final String displayName,
                           final List<String> description,
                           final GUIOptions skillGUIOptions,
                           final double initialAmount,
                           final double chancePerLevel,
                           final boolean canBeUsedWithPlayers) {
        super(id, enabled, displayName, description, skillGUIOptions, initialAmount, chancePerLevel);

        this.canBeUsedWithPlayers = canBeUsedWithPlayers;
    }

    /**
     * Adds a handle perk
     *
     * @param e {@link EntityDamagedByPlayerEvent}
     */
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void handlePerk(final EntityDamagedByPlayerEvent e) {
        final Player p = e.getPlayer();

        if (!this.canBeUsedWithPlayers && e.getEntity() instanceof Player) {
            return;
        }

        if (RandomUtil.randomBetween(0.0, 100.0) >= getChancePerLevel() * getStat(p)) {
            return;
        }
        if (!(e.getEntity() instanceof LivingEntity)) {
            return;
        }

        final LivingEntity entity = (LivingEntity) e.getEntity();

        entity.setHealth(0);
    }
}
