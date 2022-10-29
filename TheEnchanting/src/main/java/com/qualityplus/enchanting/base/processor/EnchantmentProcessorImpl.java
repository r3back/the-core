package com.qualityplus.enchanting.base.processor;

import com.qualityplus.enchanting.api.chain.EnchantmentChain;
import com.qualityplus.enchanting.api.enchantment.ICoreEnchantment;
import com.qualityplus.enchanting.api.processor.EnchantmentProcessor;
import com.qualityplus.enchanting.api.session.EnchantmentSession;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Component;
import org.bukkit.inventory.ItemStack;

@Component
public final class EnchantmentProcessorImpl implements EnchantmentProcessor {
    private @Inject EnchantmentChain chain;

    @Override
    public ItemStack addEnchantment(ItemStack itemStack, EnchantmentSession session) {
        return chain.addEnchantment(itemStack, session);
    }

    @Override
    public ItemStack removeEnchantment(ItemStack itemStack, EnchantmentSession session) {
        return chain.removeEnchantment(itemStack, session);
    }
}
