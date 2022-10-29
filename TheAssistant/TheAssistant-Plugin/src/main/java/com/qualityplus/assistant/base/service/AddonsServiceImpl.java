package com.qualityplus.assistant.base.service;

import com.qualityplus.assistant.api.addons.*;
import com.qualityplus.assistant.api.service.AddonsService;
import com.qualityplus.assistant.api.addons.PasterAddon;
import eu.okaeri.commons.bukkit.time.MinecraftTimeEquivalent;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.bukkit.annotation.Delayed;
import eu.okaeri.platform.core.annotation.Component;
import lombok.Getter;

import java.util.logging.Logger;
import java.util.stream.Stream;

@Getter
@Component
public final class AddonsServiceImpl implements AddonsService {
    private @Inject PlaceholdersAddon placeholders;
    private @Inject MythicMobsAddon mythicMobs;
    private @Inject MMOItemsAddon mmoItems;
    private @Inject EconomyAddon economy;
    private @Inject RegionAddon regions;
    private @Inject PasterAddon paster;
    private @Inject NPCAddon npc;

    @Delayed(time = MinecraftTimeEquivalent.SECOND / 20, async = true)
    public void showAddons(@Inject Logger logger){
        Stream.of(placeholders, mmoItems, economy, regions, paster, npc, mythicMobs)
                .filter(dependency -> dependency.getAddonName() != null)
                .forEach(plugin -> logger.info("Successfully hooked into " + plugin.getAddonName() + "!"));
    }
}
