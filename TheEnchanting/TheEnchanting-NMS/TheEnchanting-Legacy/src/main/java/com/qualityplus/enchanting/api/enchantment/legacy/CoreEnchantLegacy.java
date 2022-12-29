package com.qualityplus.enchanting.api.enchantment.legacy;

import com.qualityplus.enchanting.api.enchantment.ICoreEnchantment;
import com.qualityplus.enchanting.api.enchantment.ProviderType;
import lombok.Getter;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

@Getter
public abstract class CoreEnchantLegacy extends Enchantment implements ICoreEnchantment {
    protected final Map<Integer, String> requiredPermissionsToEnchant;
    protected final Map<Integer, Double> requiredXpLevelToEnchant;
    protected final Map<Integer, Double> requiredMoneyToEnchant;
    protected final Map<Integer, Double> requiredXpLevelToRemove;
    protected final Map<Integer, Double> requiredMoneyToRemove;
    protected final int requiredBookShelf;
    protected final String displayName;
    protected final String description;
    protected final int maxLevel;
    protected boolean enabled;
    protected String identifier;
    private ProviderType providerType;

    public CoreEnchantLegacy(@NotNull String identifier,@NotNull Integer id, Map<Integer, String> requiredPermissionsToEnchant, Map<Integer, Double> requiredXpLevelToEnchant, int maxLevel, boolean enabled, int requiredBookShelf,
                             Map<Integer, Double> requiredMoneyToEnchant, String displayName, String description, ProviderType providerType) {
        super(id);
        this.requiredPermissionsToEnchant = requiredPermissionsToEnchant;
        this.requiredXpLevelToEnchant = requiredXpLevelToEnchant;
        this.requiredXpLevelToRemove = requiredXpLevelToEnchant;
        this.requiredMoneyToEnchant = requiredMoneyToEnchant;
        this.requiredMoneyToRemove = requiredMoneyToEnchant;
        this.requiredBookShelf = requiredBookShelf;
        this.displayName = displayName;
        this.description = description;
        this.identifier = identifier;
        this.providerType = providerType;
        this.maxLevel = maxLevel;
        this.enabled = enabled;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }

    @Override
    public boolean canEnchant(Player player, int level) {
        return player.hasPermission(requiredPermissionsToEnchant.getOrDefault(level, ""));
    }

    @Override
    public double getRequiredLevelToEnchant(int level) {
        return requiredXpLevelToEnchant.getOrDefault(level, 1D);
    }

    @Override
    public double getRequiredMoneyToEnchant(int level) {
        return requiredMoneyToEnchant.getOrDefault(level, 1D);
    }

    @Override
    public double getRequiredLevelToRemove(int level) {
        return requiredXpLevelToRemove.getOrDefault(level, 1D);
    }

    @Override
    public double getRequiredMoneyToRemove(int level) {
        return requiredMoneyToRemove.getOrDefault(level, 1D);
    }
}
