package com.qualityplus.minions.base.minion.entity;

import com.cryptomorin.xseries.XMaterial;
import eu.okaeri.configs.OkaeriConfig;
import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class MinionEntityOptions extends OkaeriConfig {
    private String displayName;
    private XMaterial material;
    private String texture;
    private String particle;
}
