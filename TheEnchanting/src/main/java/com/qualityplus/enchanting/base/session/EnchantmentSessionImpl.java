package com.qualityplus.enchanting.base.session;

import com.qualityplus.enchanting.api.enchantment.ICoreEnchantment;
import com.qualityplus.enchanting.api.session.EnchantmentSession;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class EnchantmentSessionImpl implements EnchantmentSession {
    private final ICoreEnchantment enchantment;
    private final int level;
}
