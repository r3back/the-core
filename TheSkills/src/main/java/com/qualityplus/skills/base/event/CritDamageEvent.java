package com.qualityplus.skills.base.event;

import com.qualityplus.skills.api.event.StatEvent;
import com.qualityplus.skills.base.stat.Stat;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * Utility class for a crit damage
 */
public final class CritDamageEvent extends StatEvent {
    /**
     * Makes a crit damage
     *
     * @param who  {@link Player}
     * @param stat {@link Stat}
     */
    public CritDamageEvent(@NotNull final Player who, final Stat stat) {
        super(who, stat);
    }
}
