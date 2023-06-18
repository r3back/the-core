package com.qualityplus.minions.base.config.upgrades;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.util.faster.FastMap;
import com.qualityplus.assistant.util.time.Timer;
import com.qualityplus.minions.base.minions.minion.upgrade.MinionFuelUpgrade;
import com.qualityplus.minions.base.minions.minion.upgrade.MinionUpgrade;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Header;
import eu.okaeri.configs.annotation.NameModifier;
import eu.okaeri.configs.annotation.NameStrategy;
import eu.okaeri.configs.annotation.Names;
import eu.okaeri.platform.core.annotation.Configuration;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Configuration(path = "/upgrades/normal_upgrades.yml")
@Header("================================")
@Header("       Normal Upgrades      ")
@Header("")
@Header("percentageOfSeconds is the amount of time that will be")
@Header("removed from total, example if a minion takes 1 second and fuel is 25%")
@Header("new action time will be of 0.75 seconds.")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class NormalUpgrades extends OkaeriConfig {
    public Map<String, MinionUpgrade> normalUpgrades = FastMap.builder(String.class, MinionUpgrade.class)
            .put("minion_expander", MinionUpgrade.builder()
                    .id("minion_expander")
                    .displayName("&9Minion Expander")
                    .item(Item.builder()
                            .displayName("%minion_upgrade_display_name%")
                            .lore(Collections.singletonList("%minion_upgrade_lore%"))
                            .amount(1)
                            .material(XMaterial.COMMAND_BLOCK)
                            .build())
                    .expandsOneBlock(true)
                    .percentageOfSecondsToRemove(5)
                    .description(Arrays.asList(
                            "&7This item can be used as a",
                            "&7minion upgrade. Increases the",
                            "&7effective radius of the minion",
                            "&7by &a1 &7extra block.",
                            "",
                            "&7Increases the speed of",
                            "&7your minion by &a%minion_upgrade_percentage_seconds%%",
                            "",
                            "&9&lRARE"))

                    .build())
            .put("super_compactor_3000", MinionUpgrade.builder()
                    .id("super_compactor_3000")
                    .displayName("&9Minion Compactor 3000")
                    .item(Item.builder()
                            .displayName("%minion_upgrade_display_name%")
                            .lore(Collections.singletonList("%minion_upgrade_lore%"))
                            .amount(1)
                            .material(XMaterial.DISPENSER)
                            .build())
                    .expandsOneBlock(false)
                    .percentageOfSecondsToRemove(0)
                    .description(Arrays.asList(
                            "&7This item can be used as a",
                            "&7minion upgrade.",
                            "",
                            "&9&lRARE"))

                    .build())
            .build();

    public MinionUpgrade getById(final String id) {
        return normalUpgrades.getOrDefault(id, null);
    }
}
