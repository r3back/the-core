package com.qualityplus.assistant.base.factory;

import com.qualityplus.assistant.api.addons.PlaceholdersAddon;
import com.qualityplus.assistant.api.dependency.resolver.DependencyResolver;
import com.qualityplus.assistant.base.addons.placeholders.DefaultPlaceholdersAddon;
import com.qualityplus.assistant.base.addons.placeholders.MVDWPlaceholderAddon;
import com.qualityplus.assistant.base.addons.placeholders.PlaceholderAPIAddon;
import eu.okaeri.injector.OkaeriInjector;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Bean;
import eu.okaeri.platform.core.annotation.Component;
import org.bukkit.plugin.Plugin;

@Component
public final class PlaceholderFactory {
    private @Inject("injector") OkaeriInjector injector;
    private @Inject DependencyResolver resolver;
    private @Inject Plugin plugin;

    @Bean
    public PlaceholdersAddon configurePlaceholders() {
        if(resolver.isPlugin("PlaceholderAPI"))
            return injector.createInstance(PlaceholderAPIAddon.class);
        else if(resolver.isPlugin("MVdWPlaceholderAPI"))
            return injector.createInstance(MVDWPlaceholderAddon.class);
        else
            return injector.createInstance(DefaultPlaceholdersAddon.class);
    }
}