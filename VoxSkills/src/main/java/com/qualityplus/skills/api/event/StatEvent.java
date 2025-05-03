package com.qualityplus.skills.api.event;

import com.qualityplus.assistant.api.event.PlayerAssistantEvent;
import com.qualityplus.skills.base.stat.Stat;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
public abstract class StatEvent extends PlayerAssistantEvent {
    private Stat stat;

    public StatEvent(@NotNull Player who, Stat stat) {
        super(who);
        this.stat = stat;
    }
}
