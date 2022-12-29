package com.qualityplus.minions.base.minions.minion.level;

import com.cryptomorin.xseries.XMaterial;
import eu.okaeri.configs.OkaeriConfig;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class MatRequirement extends OkaeriConfig {
    private XMaterial requiredMaterial;
    private int requiredMaterialAmount;
}
