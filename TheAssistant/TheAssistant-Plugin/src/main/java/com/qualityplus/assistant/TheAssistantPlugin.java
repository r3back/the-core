package com.qualityplus.assistant;

import com.qualityplus.assistant.api.TheAssistantAPI;
import com.qualityplus.assistant.okaeri.OkaeriSilentPlugin;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Scan;
import eu.okaeri.platform.core.plan.ExecutionPhase;
import eu.okaeri.platform.core.plan.Planned;

import java.util.logging.Level;
import java.util.logging.Logger;

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
}
