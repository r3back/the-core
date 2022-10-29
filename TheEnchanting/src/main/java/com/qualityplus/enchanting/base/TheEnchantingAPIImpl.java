package com.qualityplus.enchanting.base;

import com.qualityplus.assistant.util.map.MapUtils;
import com.qualityplus.enchanting.api.TheEnchantingAPI;
import com.qualityplus.enchanting.api.enchantment.CoreEnchants;
import com.qualityplus.enchanting.api.enchantment.ICoreEnchantment;
import com.qualityplus.enchanting.api.processor.EnchantmentProcessor;
import com.qualityplus.enchanting.api.provider.EnchantmentProvider;
import com.qualityplus.enchanting.api.session.EnchantmentSession;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Component;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public final class TheEnchantingAPIImpl implements TheEnchantingAPI {
    private @Inject("ecoProvider") EnchantmentProvider provider;
    private @Inject EnchantmentProcessor processor;

    @Override
    public ItemStack addEnchantment(ItemStack itemStack, EnchantmentSession session) {
        return processor.addEnchantment(itemStack, session);
    }

    @Override
    public ItemStack removeEnchantment(ItemStack itemStack, EnchantmentSession session) {
        return processor.removeEnchantment(itemStack, session);
    }

    @Override
    public Optional<ICoreEnchantment> getIncompatibility(ItemStack itemStack, ICoreEnchantment enchantment) {
        return getEnchantments(itemStack)
                .keySet()
                .stream()
                .filter(itemEnch -> itemEnch.getEnchantment().equals(enchantment.getEnchantment()) != enchantment.conflictsWith(itemEnch.getEnchantment()))
                .findFirst();
    }

    @Override
    public Map<ICoreEnchantment, Integer> getEnchantments(ItemStack itemStack) {
        Map<ICoreEnchantment, Integer> enchantments = new HashMap<>();

        ItemMeta meta = itemStack.getItemMeta();

        Map<Enchantment, Integer> itemEnchants = meta instanceof EnchantmentStorageMeta ? ((EnchantmentStorageMeta) meta).getStoredEnchants(): MapUtils.check(meta.getEnchants());

        CoreEnchants.values().stream()
                .filter(enchant -> itemEnchants.containsKey(enchant.getEnchantment()))
                .forEach(enchant -> enchantments.put(enchant, itemEnchants.get(enchant.getEnchantment())));

        return enchantments;
    }
}
