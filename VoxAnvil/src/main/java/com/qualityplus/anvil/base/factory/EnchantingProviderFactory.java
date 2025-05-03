package com.qualityplus.anvil.base.factory;

import com.qualityplus.anvil.api.provider.EnchantmentProvider;
import com.qualityplus.anvil.base.provider.VoxEnchantingProvider;
import com.qualityplus.anvil.base.provider.VanillaEnchantingProvider;
import com.qualityplus.assistant.TheAssistantPlugin;
import com.qualityplus.assistant.lib.eu.okaeri.injector.annotation.Inject;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Bean;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Component;

import java.util.logging.Logger;

@Component
public final class EnchantingProviderFactory {
    private @Inject Logger logger;

    @Bean
    public EnchantmentProvider getEnchantingProvider() {
        if (TheAssistantPlugin.getAPI().getDependencyResolver().isPlugin("VoxEnchanting")) {
            return new VoxEnchantingProvider();
        } else {
            logger.warning("VoxEnchanting Plugin not found, is recommended to use with TheAnvil!");
            return new VanillaEnchantingProvider();
        }
    }
}
