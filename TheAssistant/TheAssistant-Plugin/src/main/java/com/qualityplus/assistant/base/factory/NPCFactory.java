package com.qualityplus.assistant.base.factory;

import com.qualityplus.assistant.api.addons.NPCAddon;
import com.qualityplus.assistant.api.dependency.resolver.DependencyResolver;
import com.qualityplus.assistant.base.addons.npc.CitizensAddon;
import com.qualityplus.assistant.base.addons.npc.DefaultNPCAddon;
import eu.okaeri.injector.OkaeriInjector;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Bean;
import eu.okaeri.platform.core.annotation.Component;
import org.bukkit.plugin.Plugin;

@Component
public final class NPCFactory {
    private @Inject("injector") OkaeriInjector injector;
    private @Inject DependencyResolver resolver;
    private @Inject Plugin plugin;

    @Bean
    public NPCAddon configureNpc() {
        if(resolver.isPlugin("Citizens"))
            return injector.createInstance(CitizensAddon.class);
        else
            return injector.createInstance(DefaultNPCAddon.class);
    }
}
