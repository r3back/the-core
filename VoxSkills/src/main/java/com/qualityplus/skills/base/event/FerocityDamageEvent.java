package com.qualityplus.skills.base.event;

import com.qualityplus.skills.api.event.StatEvent;
import com.qualityplus.skills.base.stat.Stat;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
public final class FerocityDamageEvent extends StatEvent {
    private final Entity victim;
    private double damage;

    public FerocityDamageEvent(@NotNull Player who, Stat stat, Entity victim, double damage) {
        super(who, stat);

        this.victim = victim;
        this.damage = damage;
    }
}
