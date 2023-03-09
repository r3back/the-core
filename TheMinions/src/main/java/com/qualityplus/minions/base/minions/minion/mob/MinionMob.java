package com.qualityplus.minions.base.minions.minion.mob;

import eu.okaeri.configs.OkaeriConfig;
import lombok.*;
import org.bukkit.entity.EntityType;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public final class MinionMob extends OkaeriConfig {
    private String displayName;
    private EntityType entityType;
    private boolean isFromMythicMobs;
    private String id;
}
