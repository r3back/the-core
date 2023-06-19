package com.qualityplus.minions.base.config.upgrades;

import com.qualityplus.assistant.lib.com.cryptomorin.xseries.XMaterial;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.util.faster.FastMap;
import com.qualityplus.assistant.util.time.HumanTime;
import com.qualityplus.minions.base.minions.minion.upgrade.MinionFuelUpgrade;
import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.Header;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.NameModifier;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.NameStrategy;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.Names;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Configuration;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

@Configuration(path = "/upgrades/fuel_upgrades.yml")
@Header("================================")
@Header("       Fuels      ")
@Header("")
@Header("percentageOfSeconds is the amount of time that will be")
@Header("removed from total, example if a minion takes 1 second and fuel is 25%")
@Header("new action time will be of 0.75 seconds.")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class FuelUpgrades extends OkaeriConfig {
    public Map<String, MinionFuelUpgrade> fuelUpgrades = FastMap.builder(String.class, MinionFuelUpgrade.class)
            .put("enchanted_coal", MinionFuelUpgrade.builder()
                    .id("enchanted_coal")
                    .displayName("&aEnchanted Coal")
                    .item(Item.builder()
                            .displayName("%minion_upgrade_display_name%")
                            .lore(Collections.singletonList("%minion_upgrade_lore%"))
                            .amount(1)
                            .material(XMaterial.COAL)
                            .build())
                    .percentageOfSecondsToRemove(40)
                    .timer(new HumanTime(30, HumanTime.TimeType.SECONDS))
                    .description(Arrays.asList(
                            "&7Increases the speed of",
                            "&7your minion by &a%minion_upgrade_percentage_seconds%%",
                            "&7for 30 seconds.",
                            "",
                            "&a&lUNCOMMON"))

                    .build())
            .build();
}
