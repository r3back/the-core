package com.qualityplus.assistant.base.factory;

import com.qualityplus.assistant.api.addons.MythicMobsAddon;
import com.qualityplus.assistant.api.dependency.resolver.DependencyResolver;
import com.qualityplus.assistant.base.addons.mythicmobs.DefaultMythicMobsAddon;
import com.qualityplus.assistant.base.addons.mythicmobs.MythicMobsAddonImpl;
import eu.okaeri.injector.OkaeriInjector;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Bean;
import eu.okaeri.platform.core.annotation.Component;
import org.bukkit.plugin.Plugin;

@Component
public final class MythicMobsFactory {
    private @Inject("injector") OkaeriInjector injector;
    private @Inject DependencyResolver resolver;
    private @Inject Plugin plugin;

    @Bean
    public MythicMobsAddon configureMythicMobs() {
        if(resolver.isPlugin("MythicMobs"))
            return tryToCreateAddon();
        else
            return injector.createInstance(DefaultMythicMobsAddon.class);
    }

    private MythicMobsAddon tryToCreateAddon(){
        try {
            Class.forName("io.lumine.mythic.bukkit.BukkitAPIHelper");

            return injector.createInstance(MythicMobsAddonImpl.class);
        }catch (Exception e){
            return injector.createInstance(DefaultMythicMobsAddon.class);
        }
    }
}
