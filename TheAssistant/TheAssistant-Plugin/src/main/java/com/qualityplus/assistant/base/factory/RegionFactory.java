package com.qualityplus.assistant.base.factory;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.assistant.api.addons.RegionAddon;
import com.qualityplus.assistant.api.dependency.resolver.DependencyResolver;
import com.qualityplus.assistant.base.addons.regions.DefaultRegionsAddon;
import com.qualityplus.assistant.base.addons.regions.ResidenceAddon;
import com.qualityplus.assistant.base.addons.regions.UltraRegionsAddon;
import com.qualityplus.assistant.base.addons.regions.WG7Addon;
import eu.okaeri.injector.OkaeriInjector;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Bean;
import com.qualityplus.assistant.base.addons.regions.WG6Addon;
import eu.okaeri.platform.core.annotation.Component;

@Component
public final class RegionFactory {
    private @Inject("injector") OkaeriInjector injector;
    private @Inject DependencyResolver resolver;

    @Bean
    private RegionAddon configureRegions() {
        if(resolver.isPlugin("WorldGuard"))
            return injector.createInstance(getWorldGuard());
        else if(resolver.isPlugin("Residence"))
            return injector.createInstance(ResidenceAddon.class);
        else if(resolver.isPlugin("UltraRegions"))
            return injector.createInstance(UltraRegionsAddon.class);
        else
            return injector.createInstance(DefaultRegionsAddon.class);
    }

    private Class<? extends RegionAddon> getWorldGuard(){
        return XMaterial.getVersion() > 12 ? WG7Addon.class : WG6Addon.class;
    }
}
