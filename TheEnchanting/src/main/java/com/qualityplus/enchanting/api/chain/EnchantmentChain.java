package com.qualityplus.enchanting.api.chain;

import com.qualityplus.enchanting.api.enchantment.ICoreEnchantment;
import com.qualityplus.enchanting.api.session.EnchantmentSession;
import org.bukkit.inventory.ItemStack;

public interface EnchantmentChain {
    ItemStack addEnchantment(ItemStack itemStack, EnchantmentSession session);

    ItemStack removeEnchantment(ItemStack itemStack, EnchantmentSession session);
}
