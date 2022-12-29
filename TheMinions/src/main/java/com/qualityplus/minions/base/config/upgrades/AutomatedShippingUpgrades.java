package com.qualityplus.minions.base.config.upgrades;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.util.faster.FasterMap;
import com.qualityplus.minions.base.minions.minion.upgrade.MinionAutoShipping;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Header;
import eu.okaeri.configs.annotation.NameModifier;
import eu.okaeri.configs.annotation.NameStrategy;
import eu.okaeri.configs.annotation.Names;
import eu.okaeri.platform.core.annotation.Configuration;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

@Configuration(path = "/upgrades/automated_shipping_upgrades.yml")
@Header("================================")
@Header("       Automated Shipping      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class AutomatedShippingUpgrades extends OkaeriConfig {
    public Map<String, MinionAutoShipping> automatedShippingUpgrades = FasterMap.builder(String.class, MinionAutoShipping.class)
            .put("budget_hopper", MinionAutoShipping.builder()
                    .id("budget_hopper")
                    .displayName("&aBudget Hopper")
                    .item(Item.builder()
                            .displayName("%minion_upgrade_display_name%")
                            .lore(Collections.singletonList("%minion_upgrade_lore%"))
                            .amount(1)
                            .material(XMaterial.HOPPER)
                            .build())
                    .percentageOfPriceToSell(50)
                    .description(Arrays.asList(
                            "&7This item can be placed inside",
                            "&7any minion. Automatically sells",
                            "&7generated items when the minion",
                            "&7has no space. Items are sold for",
                            "&a%minion_upgrade_percentage_price%% &7of their selling price.",
                            "",
                            "&a&lUNCOMMON"))

                    .build())
            .build();
}
