package com.qualityplus.collections.base.addons;

import com.qualityplus.assistant.TheAssistantPlugin;
import com.qualityplus.assistant.api.addons.PlaceholdersAddon;
import com.qualityplus.assistant.api.addons.registrable.Registrable;
import com.qualityplus.collections.api.service.CollectionsService;
import com.qualityplus.collections.base.collection.Collection;
import com.qualityplus.collections.base.collection.registry.CollectionsRegistry;
import eu.okaeri.commons.bukkit.time.MinecraftTimeEquivalent;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.bukkit.annotation.Delayed;
import eu.okaeri.platform.core.annotation.Component;

@Component
public final class PlaceholdersRegistry {
    @Delayed(time = MinecraftTimeEquivalent.SECOND * 5)
    public void registerSkillsPlaceholders(@Inject CollectionsService service){
        PlaceholdersAddon addon = TheAssistantPlugin.getAPI().getAddons().getPlaceholders();

        for(Collection collection : CollectionsRegistry.values()){
            addon.registerPlaceholders("collection_" + collection.getId() + "_xp",
                    e -> String.valueOf(service.getCollectionsData(e.getPlayer().getUniqueId()).map(data -> data.getCollections().getXp(collection.getId())).orElse(0D)));
            addon.registerPlaceholders("collection_" + collection.getId() + "_level",
                    e -> String.valueOf(service.getCollectionsData(e.getPlayer().getUniqueId()).map(data -> data.getCollections().getLevel(collection.getId())).orElse(0)));
        }

        if(addon instanceof Registrable) ((Registrable) addon).registerAddon();
    }
}
