package com.qualityplus.minions.base.minions.minion.update;

import com.qualityplus.minions.base.minions.minion.update.item.ItemSettings;
import com.qualityplus.minions.base.minions.minion.update.item.UpgradeSettings;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import lombok.*;

import java.util.List;
import java.util.Map;

@Data
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public final class MinionSettings extends OkaeriConfig {
    @Comment("The final price of base Item")
    private double normalSellPrice;
    @Comment("The Item that minion give by default")
    private ItemSettings baseItem;
    @Comment("Specific settings by each upgrade")
    private Map<String, UpgradeSettings> upgradeSettings;
    @Comment("If this minion can use fuel")
    private boolean canUseFuel;
    @Comment("If this minion can use Auto Shipping")
    private boolean canUseAutoShipping;
    @Comment("Id's of upgrades that are blocked")
    private List<String> blockedUpgrades;
}
