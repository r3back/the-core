package com.qualityplus.enchanting.base.factory.hook;

import com.qualityplus.enchanting.api.provider.EnchantmentProvider;
import com.qualityplus.enchanting.base.hook.EcoHookSettings;
import com.qualityplus.enchanting.base.provider.EcoEnchantmentProvider;
import com.qualityplus.enchanting.base.provider.VanillaEnchantmentProvider;
import com.qualityplus.enchanting.util.EnchantingAddonsUtil;
import eu.okaeri.commons.bukkit.time.MinecraftTimeEquivalent;
import eu.okaeri.injector.OkaeriInjector;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.bukkit.annotation.Delayed;
import eu.okaeri.platform.core.annotation.Bean;
import eu.okaeri.platform.core.annotation.Component;

@Component
public final class EcoProviderFactory {
    private @Inject("injector") OkaeriInjector injector;

    @Bean("ecoProvider")
    private EnchantmentProvider setupEco(){
        if(EnchantingAddonsUtil.ECO_ENCHANTMENTS) {
            return injector.createInstance(EcoEnchantmentProvider.class);
        }else
            return injector.createInstance(VanillaEnchantmentProvider.class);
    }

    @Delayed(time = MinecraftTimeEquivalent.SECOND)
    private void sendEcoIncompatibilityMessage(){
        if(!EnchantingAddonsUtil.ECO_ENCHANTMENTS) return;

        injector.createInstance(EcoHookSettings.class).sendWarning();
    }
}
