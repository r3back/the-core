package com.qualityplus.assistant;

import com.qualityplus.assistant.api.TheAssistantAPI;
import com.qualityplus.assistant.api.config.ConfigSlimeWorldManager;
import com.qualityplus.assistant.base.config.Config;
import com.qualityplus.assistant.okaeri.OkaeriSilentPlugin;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Bean;
import eu.okaeri.platform.core.annotation.Scan;

@Scan(deep = true, exclusions = {
        "com.qualityplus.assistant.lib",
        "com.qualityplus.assistant.base.addons.placeholders",
        "com.qualityplus.assistant.inventory"
})
public final class TheAssistantPlugin extends OkaeriSilentPlugin {
    private static @Inject TheAssistantAPI api;

    public static TheAssistantAPI getAPI(){
        return api;
    }

    @Bean
    public ConfigSlimeWorldManager setupSlimeWorld(@Inject Config config){
        return config.configSlimeWorldManager;
    }
}
