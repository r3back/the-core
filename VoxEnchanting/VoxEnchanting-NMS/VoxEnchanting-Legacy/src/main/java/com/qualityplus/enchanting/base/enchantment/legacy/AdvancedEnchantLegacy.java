package com.qualityplus.enchanting.base.enchantment.legacy;

import com.qualityplus.enchanting.api.enchantment.ProviderType;
import org.bukkit.enchantments.Enchantment;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public final class AdvancedEnchantLegacy extends VanillaEnchantLegacy {
    public AdvancedEnchantLegacy(@NotNull String identifier, Integer id, Map<Integer, String> requiredPermissionsToEnchant, Map<Integer, Double> requiredXpLevelToEnchant, int maxLevel,
                                 boolean enabled, int requiredBookShelf, Map<Integer, Double> requiredMoneyToEnchant, String displayName, String description, Enchantment enchantment,
                                 ProviderType providerType, Map<Integer, String> descriptionPerLevel) {
        super(identifier, id, requiredPermissionsToEnchant, requiredXpLevelToEnchant, maxLevel, enabled, requiredBookShelf, requiredMoneyToEnchant, displayName, description, enchantment, providerType, descriptionPerLevel);
    }
}
