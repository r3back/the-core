package com.qualityplus.anvil.base.factory

import com.qualityplus.anvil.api.provider.EnchantmentProvider
import com.qualityplus.anvil.base.provider.TheEnchantingProvider
import com.qualityplus.anvil.base.provider.VanillaEnchantingProvider
import com.qualityplus.assistant.TheAssistantPlugin
import eu.okaeri.injector.annotation.Inject
import eu.okaeri.platform.core.annotation.Bean
import eu.okaeri.platform.core.annotation.Component
import java.util.logging.Logger

@Component
class EnchantingProviderFactory {
    @Inject
    private val logger: Logger? = null

    @get:Bean
    val enchantingProvider: EnchantmentProvider
        get() = if (TheAssistantPlugin.getAPI().dependencyResolver.isPlugin("TheEnchanting")) {
            TheEnchantingProvider()
        } else {
            logger!!.warning("TheEnchanting Plugin not found, is recommended to use with TheAnvil!")
            VanillaEnchantingProvider()
        }
}