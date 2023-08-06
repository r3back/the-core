package com.qualityplus.skills.api.event;

import com.qualityplus.assistant.api.event.PlayerAssistantEvent;
import com.qualityplus.skills.base.perk.Perk;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * Utility class for perk event
 */
@Getter
@Setter
public abstract class PerkEvent extends PlayerAssistantEvent {
    private Perk perk;

    /**
     * Adds a perk
     *
     * @param who  {@link Player}
     * @param perk {@link Perk}
     */
    public PerkEvent(@NotNull final Player who, final Perk perk) {
        super(who);
        this.perk = perk;
    }
}
