package com.qualityplus.enchanting.api.session;

import com.qualityplus.enchanting.api.enchantment.ICoreEnchantment;

public interface EnchantmentSession {
    ICoreEnchantment getEnchantment();

    int getLevel();
}
