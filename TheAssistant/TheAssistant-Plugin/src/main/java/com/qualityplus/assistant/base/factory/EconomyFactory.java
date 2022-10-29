package com.qualityplus.assistant.base.factory;

import com.qualityplus.assistant.api.addons.EconomyAddon;
import com.qualityplus.assistant.api.dependency.resolver.DependencyResolver;
import com.qualityplus.assistant.base.addons.economy.DefaultEconomyAddon;
import com.qualityplus.assistant.base.addons.economy.PlayerPointsAddon;
import com.qualityplus.assistant.base.addons.economy.TokenManagerAddon;
import com.qualityplus.assistant.base.addons.economy.VaultAddon;
import eu.okaeri.injector.OkaeriInjector;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Bean;
import eu.okaeri.platform.core.annotation.Component;
import org.bukkit.plugin.Plugin;

@Component
public final class EconomyFactory {
    private @Inject("injector") OkaeriInjector injector;
    private @Inject DependencyResolver resolver;
    private @Inject Plugin plugin;

    @Bean
    public EconomyAddon configureEconomy() {
        if(resolver.isPlugin("Vault"))
            return injector.createInstance(VaultAddon.class);
        else if(resolver.isPlugin("PlayerPoints"))
            return injector.createInstance(PlayerPointsAddon.class);
        else if(resolver.isPlugin("TokenManager"))
            return injector.createInstance(TokenManagerAddon.class);
        else
            return injector.createInstance(DefaultEconomyAddon.class);
    }
}
