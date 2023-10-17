package com.qualityplus.skills.base.event;

import com.qualityplus.skills.api.event.StatEvent;
import com.qualityplus.skills.base.stat.Stat;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * Utility class for ferocity damage
 */
@Getter
@Setter
public final class FerocityDamageEvent extends StatEvent {
    private final Entity victim;
    private double damage;

    /**
     * Makes a ferocity damage
     *
     * @param who    {@link Player}
     * @param stat   {@link Stat}
     * @param victim {@link Entity}
     * @param damage Damage
     */
    public FerocityDamageEvent(@NotNull final Player who, final Stat stat, final Entity victim, final double damage) {
        super(who, stat);

        this.victim = victim;
        this.damage = damage;
    }
}
