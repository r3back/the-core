package com.qualityplus.collections;

import com.qualityplus.assistant.okaeri.OkaeriSilentPlugin;
import com.qualityplus.collections.api.TheCollectionsAPI;
import com.qualityplus.collections.api.box.Box;
import com.qualityplus.collections.persistance.data.UserData;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Scan;
import eu.okaeri.platform.core.plan.ExecutionPhase;
import eu.okaeri.platform.core.plan.Planned;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@Scan(deep = true)
public final class TheCollections extends OkaeriSilentPlugin {
    private static @Inject @Getter TheCollectionsAPI api;

    @Planned(ExecutionPhase.PRE_SHUTDOWN)
    private void whenStop(Box box){
        Bukkit.getOnlinePlayers()
                .stream()
                .map(Player::getUniqueId)
                .forEach(uuid -> box.service().getCollectionsData(uuid).ifPresent(UserData::save));
    }
}
