package com.qualityplus.souls.base.addons;

import com.qualityplus.assistant.TheAssistantPlugin;
import com.qualityplus.assistant.api.addons.PlaceholdersAddon;
import com.qualityplus.assistant.api.addons.registrable.Registrable;
import com.qualityplus.souls.api.service.SoulsService;
import com.qualityplus.souls.base.config.Souls;
import com.qualityplus.souls.persistance.data.SoulsData;
import eu.okaeri.commons.bukkit.time.MinecraftTimeEquivalent;

import eu.okaeri.platform.bukkit.annotation.Delayed;
import eu.okaeri.platform.core.annotation.Component;

import java.util.List;
import java.util.stream.Stream;

@Component
public final class PlaceholdersRegistry {
    @Delayed(time = MinecraftTimeEquivalent.SECOND * 5)
    public void registerBankPlaceholders(@Inject SoulsService service, @Inject Souls file){
        PlaceholdersAddon addon = TheAssistantPlugin.getAPI().getAddons().getPlaceholders();

        addon.registerPlaceholders("souls_user_amount",
                e -> String.valueOf(service.getData(e.getPlayer().getUniqueId()).map(SoulsData::getSoulsCollected).map(List::size).orElse(0)));

        addon.registerPlaceholders("souls_server_amount",
                e -> String.valueOf(file.soulList.size()));

        addon.registerPlaceholders("souls_user_tia_amount",
                e -> String.valueOf(service.getData(e.getPlayer().getUniqueId()).map(SoulsData::getTiaSoulsCollected).map(List::size).orElse(0)));

        Stream.of(addon)
                .filter(a -> a instanceof Registrable)
                .map(a -> (Registrable) a)
                .forEach(Registrable::registerAddon);
    }
}
