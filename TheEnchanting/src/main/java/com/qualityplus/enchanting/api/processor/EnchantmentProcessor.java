package com.qualityplus.enchanting.api.processor;

import com.qualityplus.enchanting.api.enchantment.ICoreEnchantment;
import com.qualityplus.enchanting.api.session.EnchantmentSession;
import org.bukkit.inventory.ItemStack;

public interface EnchantmentProcessor {
    ItemStack addEnchantment(ItemStack itemStack, EnchantmentSession session);

    ItemStack removeEnchantment(ItemStack itemStack, EnchantmentSession session);
}
