package com.qualityplus.enchanting.api.provider;

import com.qualityplus.enchanting.api.enchantment.ICoreEnchantment;

import java.util.List;

public interface EnchantmentProvider {
    List<ICoreEnchantment> getEnchantments();
}
