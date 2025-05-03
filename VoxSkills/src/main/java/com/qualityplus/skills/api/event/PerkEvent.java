package com.qualityplus.skills.api.event;

import com.qualityplus.assistant.api.event.PlayerAssistantEvent;
import com.qualityplus.skills.base.perk.Perk;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
public abstract class PerkEvent extends PlayerAssistantEvent {
    private Perk perk;

    public PerkEvent(@NotNull Player who, Perk perk) {
        super(who);
        this.perk = perk;
    }
}
