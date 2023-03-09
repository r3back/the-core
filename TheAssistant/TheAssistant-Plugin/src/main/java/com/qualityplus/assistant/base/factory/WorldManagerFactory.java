package com.qualityplus.assistant.base.factory;

import com.qualityplus.assistant.api.addons.WorldManagerAddon;
import com.qualityplus.assistant.api.dependency.resolver.DependencyResolver;
import com.qualityplus.assistant.base.addons.worldmanager.DefaultWorldManagerAddon;
import com.qualityplus.assistant.base.addons.worldmanager.SlimeWorldAddon;
import eu.okaeri.injector.OkaeriInjector;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Bean;
import eu.okaeri.platform.core.annotation.Component;
import org.bukkit.plugin.Plugin;

@Component
public final class WorldManagerFactory {
    private @Inject("injector") OkaeriInjector injector;
    private @Inject DependencyResolver resolver;
    private @Inject Plugin plugin;

    @Bean
    public WorldManagerAddon configureSlimeWorldManager() {
        if(resolver.isPlugin("SlimeWorldManager"))
            return injector.createInstance(SlimeWorldAddon.class);
        else
            return injector.createInstance(DefaultWorldManagerAddon.class);
    }
}
