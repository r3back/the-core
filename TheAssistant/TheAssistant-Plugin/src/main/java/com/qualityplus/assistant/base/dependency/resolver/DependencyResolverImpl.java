package com.qualityplus.assistant.base.dependency.resolver;

import com.qualityplus.assistant.api.dependency.resolver.DependencyResolver;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Component;
import org.bukkit.plugin.Plugin;

@Component
public final class DependencyResolverImpl implements DependencyResolver {
    private @Inject Plugin plugin;

    @Override
    public boolean isPlugin(String name){
        return plugin.getServer().getPluginManager().getPlugin(name) != null;
    }
}
