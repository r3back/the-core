package com.qualityplus.enchanting.base.provider;

import com.qualityplus.enchanting.api.enchantment.ICoreEnchantment;
import com.qualityplus.enchanting.api.provider.EnchantmentProvider;

import java.util.Collections;
import java.util.List;

public final class VanillaEnchantmentProvider implements EnchantmentProvider {
    @Override
    public List<ICoreEnchantment> getEnchantments() {
        return Collections.emptyList();
    }
}
