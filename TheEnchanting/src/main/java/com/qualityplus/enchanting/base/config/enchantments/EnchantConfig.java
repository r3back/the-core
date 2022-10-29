package com.qualityplus.enchanting.base.config.enchantments;

import eu.okaeri.configs.OkaeriConfig;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@RequiredArgsConstructor
public final class EnchantConfig extends OkaeriConfig {
    private final String displayName;
    private final String description;
    private boolean enabled = true;
    private int requiredBookShelf = 0;
    private final Map<Integer, String> requiredPermissionsToEnchant;
    private final Map<Integer, Double> requiredXpLevelToEnchant;
    private final Map<Integer, Double> requiredMoneyToEnchant;
}
