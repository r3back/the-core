package com.qualityplus.enchanting.base.provider;

import com.qualityplus.assistant.lib.eu.okaeri.injector.annotation.Inject;
import com.qualityplus.enchanting.api.enchantment.ICoreEnchantment;
import com.qualityplus.enchanting.api.enchantment.ProviderType;
import com.qualityplus.enchanting.api.provider.EnchantmentProvider;
import com.qualityplus.enchanting.base.config.enchantments.EnchantConfig;
import com.qualityplus.enchanting.base.config.enchantments.eco.EcoEnchantments;
import com.qualityplus.enchanting.base.factory.VanillaEnchantmentFactory;
import com.willfp.ecoenchants.enchants.EcoEnchant;
import com.willfp.ecoenchants.enchants.EcoEnchants;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public final class EcoEnchantmentProvider implements EnchantmentProvider {
    private @Inject EcoEnchantments config;

    @Override
    public List<ICoreEnchantment> getEnchantments() {
        return EcoEnchants.values()
                .stream()
                .map(this::build)
                .collect(Collectors.toList());
    }

    private ICoreEnchantment build(EcoEnchant ecoEnchant){
        String id = ecoEnchant.getKey().getKey();

        Optional<EnchantConfig> conf = Optional.ofNullable(config.customEcoOptions.getOrDefault(id, null));

        return VanillaEnchantmentFactory.builder()
                .identifier(id)
                .requiredPermissionsToEnchant(conf.map(EnchantConfig::getRequiredPermissionsToEnchant).orElse(new HashMap<>()))
                .requiredXpLevelToEnchant(conf.map(EnchantConfig::getRequiredXpLevelToEnchant).orElse(new HashMap<>()))
                .requiredMoneyToEnchant(conf.map(EnchantConfig::getRequiredMoneyToEnchant).orElse(new HashMap<>()))
                .description(ecoEnchant.getUnformattedDescription(1))
                .descriptionPerLevel(descriptionPerLevel(ecoEnchant))
                .displayName(ecoEnchant.getDisplayName())
                .maxLevel(ecoEnchant.getMaxLevel())
                .enchantment(ecoEnchant)
                .requiredBookShelf(1)
                .enabled(true)
                .build()
                .build(ProviderType.ECO_ENCHANT);
    }

    private Map<Integer, String> descriptionPerLevel(EcoEnchant ecoEnchant){
        Map<Integer, String> map = new HashMap<>();

        for(int i = 1; i<=ecoEnchant.getMaxLevel(); i++)
            map.put(i, ecoEnchant.getUnformattedDescription(i));

        return map;
    }
}
