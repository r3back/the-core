package com.qualityplus.enchanting.base.enchantment.newest;

import com.qualityplus.enchanting.api.enchantment.ProviderType;
import com.qualityplus.enchanting.api.enchantment.VanillaEnchantment;
import com.qualityplus.enchanting.api.enchantment.newest.CoreEnchantNewest1_20;
import lombok.Builder;
import lombok.Getter;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Optional;

public final class VanillaEnchantNewest1_20 extends CoreEnchantNewest1_20 implements VanillaEnchantment {
    private final Map<Integer, String> descriptionPerLevel;
    @Getter
    private final Enchantment enchantment;
    private final NamespacedKey key;

    @Builder
    public VanillaEnchantNewest1_20(@NotNull String identifier, Map<Integer, String> requiredPermissionsToEnchant, Map<Integer, Double> requiredXpLevelToEnchant, int maxLevel, boolean enabled, int requiredBookShelf,
                                Map<Integer, Double> requiredMoneyToEnchant, String displayName, String description, Enchantment enchantment, ProviderType providerType, Map<Integer, String> descriptionPerLevel) {
        super(identifier, requiredPermissionsToEnchant, requiredXpLevelToEnchant, maxLevel, enabled, requiredBookShelf, requiredMoneyToEnchant, displayName, description, providerType);

        this.key = NamespacedKey.minecraft(identifier);
        this.descriptionPerLevel = descriptionPerLevel;
        this.enchantment = enchantment;
    }

    @Override
    public String getDescriptionPerLevel(int level) {
        return Optional.ofNullable(descriptionPerLevel)
                .filter(map -> map.containsKey(level))
                .map(map -> map.get(level))
                .orElse("");
    }

    @Override
    public @NotNull String getName() {
        return this.displayName;
    }

    @Override
    public @NotNull EnchantmentTarget getItemTarget() {
        return Optional.ofNullable(this.enchantment)
                .map(Enchantment::getItemTarget)
                .orElse(EnchantmentTarget.ALL);
    }

    @Override
    public boolean isTreasure() {
        return Optional.ofNullable(this.enchantment)
                .map(Enchantment::isTreasure)
                .orElse(false);
    }

    @Override
    public boolean isCursed() {
        return Optional.ofNullable(this.enchantment)
                .map(Enchantment::isCursed)
                .orElse(false);
    }

    @Override
    public boolean conflictsWith(@NotNull Enchantment other) {
        return Optional.ofNullable(this.enchantment)
                .map(enchantment1 -> enchantment1.conflictsWith(other))
                .orElse(false);
    }

    @Override
    public boolean canEnchantItem(@NotNull ItemStack item) {
        return Optional.ofNullable(this.enchantment)
                .map(enchantment1 -> enchantment1.canEnchantItem(item))
                .orElse(false);
    }

    @NotNull
    @Override
    public NamespacedKey getKey() {
        return key;
    }

    @NotNull
    @Override
    public String getTranslationKey() {
        return key.getKey();
    }
}
