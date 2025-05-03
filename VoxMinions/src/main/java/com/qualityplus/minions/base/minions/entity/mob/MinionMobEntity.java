package com.qualityplus.minions.base.minions.entity.mob;

import lombok.*;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

@Data
@AllArgsConstructor
@Builder
public final class MinionMobEntity {
    private final Location location;
    private final Entity entity;
}
