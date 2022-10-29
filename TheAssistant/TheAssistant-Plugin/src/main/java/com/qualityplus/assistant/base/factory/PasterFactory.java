package com.qualityplus.assistant.base.factory;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.assistant.base.addons.paster.DefaultPasterAddon;
import com.qualityplus.assistant.base.addons.paster.WorldEdit6;
import com.qualityplus.assistant.api.addons.PasterAddon;
import com.qualityplus.assistant.api.dependency.resolver.DependencyResolver;
import com.qualityplus.assistant.base.addons.paster.WorldEdit7;
import eu.okaeri.injector.OkaeriInjector;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Bean;
import eu.okaeri.platform.core.annotation.Component;
import org.bukkit.plugin.Plugin;

import java.util.logging.Logger;

@Component
public final class PasterFactory {
    private @Inject("injector") OkaeriInjector injector;
    private @Inject DependencyResolver resolver;
    private @Inject Plugin plugin;
    private @Inject Logger logger;

    @Bean
    private PasterAddon configurePaster() {
        return !resolver.isPlugin("WorldEdit") ? injector.createInstance(DefaultPasterAddon.class) : getPaster(XMaterial.getVersion() > 13 ? WorldEdit7.class : WorldEdit6.class);
    }

    private PasterAddon getPaster(Class<? extends PasterAddon> paster){
        return injector.createInstance(paster);
    }

    private boolean isWorldEdit(){
        return resolver.isPlugin("WorldEdit") || resolver.isPlugin("FastAsyncWorldEdit") || resolver.isPlugin("AsyncWorldEdit");
    }
}