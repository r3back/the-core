package com.qualityplus.enchanting.api.handler;

import com.qualityplus.enchanting.api.enchantment.ICoreEnchantment;

public interface EnchantmentController<T> {
    T addEnchantment(T itemStack, ICoreEnchantment enchantment, int level);
    T removeEnchantment(T itemStack, ICoreEnchantment enchantment);
}
