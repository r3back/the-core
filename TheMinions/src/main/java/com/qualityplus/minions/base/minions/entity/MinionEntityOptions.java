package com.qualityplus.minions.base.minions.entity;

import eu.okaeri.configs.OkaeriConfig;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public final class MinionEntityOptions extends OkaeriConfig {
    private String displayName;
    private String particle;
}
