package com.qualityplus.skills.api.event;

import com.qualityplus.assistant.api.event.PlayerAssistantEvent;
import com.qualityplus.skills.base.stat.Stat;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * Utility class for player assistant event
 */
@Getter
@Setter
public abstract class StatEvent extends PlayerAssistantEvent {
    private Stat stat;

    /**
     * Adds a stats
     *
     * @param who  {@link Player}
     * @param stat {@link Stat}
     */
    public StatEvent(@NotNull final Player who, final Stat stat) {
        super(who);
        this.stat = stat;
    }
}
