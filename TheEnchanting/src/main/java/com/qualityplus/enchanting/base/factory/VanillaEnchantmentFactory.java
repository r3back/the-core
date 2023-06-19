package com.qualityplus.enchanting.base.factory;

import com.qualityplus.assistant.lib.com.cryptomorin.xseries.XMaterial;
import com.qualityplus.enchanting.api.enchantment.ICoreEnchantment;
import com.qualityplus.enchanting.api.enchantment.ProviderType;
import com.qualityplus.enchanting.base.enchantment.legacy.VanillaEnchantLegacy;
import com.qualityplus.enchanting.base.enchantment.newest.VanillaEnchantNewest;
import lombok.Builder;
import org.bukkit.enchantments.Enchantment;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public final class VanillaEnchantmentFactory {
    private static final AtomicInteger ENCHANTMENT_COUNTER = new AtomicInteger(100);
    private final Map<Integer, String> requiredPermissionsToEnchant;
    private final Map<Integer, Double> requiredXpLevelToEnchant;
    private final Map<Integer, Double> requiredMoneyToEnchant;
    private final Map<Integer, String> descriptionPerLevel;
    private final Enchantment enchantment;
    private final int requiredBookShelf;
    private final String displayName;
    private final String description;
    private final String identifier;
    private final boolean enabled;
    private final int maxLevel;

    @Builder
    public VanillaEnchantmentFactory(@NotNull String identifier, Map<Integer, String> requiredPermissionsToEnchant, Map<Integer, Double> requiredXpLevelToEnchant, int maxLevel, boolean enabled, int requiredBookShelf,
                                     Map<Integer, Double> requiredMoneyToEnchant, String displayName, String description, Enchantment enchantment, Map<Integer, String> descriptionPerLevel) {
        this.requiredPermissionsToEnchant = requiredPermissionsToEnchant;
        this.requiredXpLevelToEnchant = requiredXpLevelToEnchant;
        this.requiredMoneyToEnchant = requiredMoneyToEnchant;
        this.descriptionPerLevel = descriptionPerLevel;
        this.requiredBookShelf = requiredBookShelf;
        this.enchantment = enchantment;
        this.displayName = displayName;
        this.description = description;
        this.identifier = identifier;
        this.maxLevel = maxLevel;
        this.enabled = enabled;
    }

    public ICoreEnchantment build(ProviderType providerType){
        if(XMaterial.getVersion() > 12){
            return VanillaEnchantNewest.builder()
                    .requiredPermissionsToEnchant(requiredPermissionsToEnchant)
                    .requiredXpLevelToEnchant(requiredXpLevelToEnchant)
                    .requiredMoneyToEnchant(requiredMoneyToEnchant)
                    .descriptionPerLevel(descriptionPerLevel)
                    .requiredBookShelf(requiredBookShelf)
                    .providerType(providerType)
                    .enchantment(enchantment)
                    .displayName(displayName)
                    .description(description)
                    .identifier(identifier)
                    .maxLevel(maxLevel)
                    .enabled(enabled)
                    .build();
        }else{
            return VanillaEnchantLegacy.builder()
                    .requiredPermissionsToEnchant(requiredPermissionsToEnchant)
                    .requiredXpLevelToEnchant(requiredXpLevelToEnchant)
                    .requiredMoneyToEnchant(requiredMoneyToEnchant)
                    .descriptionPerLevel(descriptionPerLevel)
                    .id(ENCHANTMENT_COUNTER.getAndIncrement())
                    .requiredBookShelf(requiredBookShelf)
                    .providerType(providerType)
                    .displayName(displayName)
                    .description(description)
                    .enchantment(enchantment)
                    .identifier(identifier)
                    .maxLevel(maxLevel)
                    .enabled(enabled)
                    .build();
        }
    }
}
