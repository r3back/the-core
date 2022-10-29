package com.qualityplus.enchanting.api.enchantment;

import com.google.common.collect.ImmutableSet;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class CoreEnchants {
    private static final Map<String, ICoreEnchantment> ENCHANTMENT_REGISTRY = new HashMap<>();

    @ApiStatus.Internal
    public static void registerNewEnchantment(@NotNull final ICoreEnchantment enchantment) {
        ENCHANTMENT_REGISTRY.put(enchantment.getIdentifier().toLowerCase(), enchantment);
    }

    @Nullable
    public static ICoreEnchantment getByID(@NotNull final String id) {
        return ENCHANTMENT_REGISTRY.get(id.toLowerCase());
    }

    @Nullable
    public static ICoreEnchantment getByKey(@NotNull final NamespacedKey key) {
        return ENCHANTMENT_REGISTRY.get(key.getKey());
    }

    @Nullable
    public static ICoreEnchantment removeByID(@NotNull final String id) {
        return ENCHANTMENT_REGISTRY.remove(id.toLowerCase());
    }

    @Nullable
    public static ICoreEnchantment removeByKey(@NotNull final NamespacedKey key) {
        return ENCHANTMENT_REGISTRY.remove(key.getKey());
    }

    public static Set<ICoreEnchantment> values() {
        return ImmutableSet.copyOf(ENCHANTMENT_REGISTRY.values());
    }
}
