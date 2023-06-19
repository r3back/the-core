package com.qualityplus.minions.base.minions.minion.update.item;

import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public final class UpgradeSettings extends OkaeriConfig {
    private double sellPrice;
    private ItemSettings itemSettings;
}
