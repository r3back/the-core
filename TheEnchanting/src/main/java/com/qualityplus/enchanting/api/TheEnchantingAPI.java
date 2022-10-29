package com.qualityplus.enchanting.api;

import com.qualityplus.enchanting.api.enchantment.ICoreEnchantment;
import com.qualityplus.enchanting.api.session.EnchantmentSession;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.Optional;

public interface TheEnchantingAPI {
    ItemStack addEnchantment(ItemStack itemStack, EnchantmentSession session);

    ItemStack removeEnchantment(ItemStack itemStack, EnchantmentSession session);

    Map<ICoreEnchantment, Integer> getEnchantments(ItemStack itemStack);

    Optional<ICoreEnchantment> getIncompatibility(ItemStack itemStack, ICoreEnchantment enchantment);
}
