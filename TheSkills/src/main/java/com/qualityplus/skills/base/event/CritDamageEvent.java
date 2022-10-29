package com.qualityplus.skills.base.event;

import com.qualityplus.skills.api.event.StatEvent;
import com.qualityplus.skills.base.stat.Stat;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public final class CritDamageEvent extends StatEvent {
    public CritDamageEvent(@NotNull Player who, Stat stat) {
        super(who, stat);
    }
}
