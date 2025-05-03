package com.qualityplus.runes.base.event;

import com.qualityplus.runes.api.event.PlayerRuneEvent;
import com.qualityplus.runes.api.session.RuneSession;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@Getter
public final class RuneTableUseEvent extends PlayerRuneEvent {
    private final RuneSession session;

    public RuneTableUseEvent(@NotNull Player who, RuneSession session) {
        super(who);

        this.session = session;
    }
}
