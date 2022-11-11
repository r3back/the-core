package com.qualityplus.minions;

import com.qualityplus.assistant.okaeri.OkaeriSilentPlugin;
import com.qualityplus.minions.api.TheSoulsAPI;
import com.qualityplus.minions.api.box.Box;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Scan;
import eu.okaeri.platform.core.plan.ExecutionPhase;
import eu.okaeri.platform.core.plan.Planned;
import lombok.Getter;

@Scan(deep = true)
public final class TheMinions extends OkaeriSilentPlugin {
    private static @Inject @Getter TheSoulsAPI api;
    private static TheMinions INSTANCE;

    public static TheMinions getInstance() {
        return INSTANCE;
    }

    @Planned(ExecutionPhase.POST_STARTUP)
    private void whenStart() {
        INSTANCE = this;
    }

    @Planned(ExecutionPhase.PRE_SHUTDOWN)
    private void whenStop(@Inject Box box) {
        /*box.files().souls().soulList.forEach(Soul::disable);

        box.files().souls().save();

        Bukkit.getOnlinePlayers()
                .stream()
                .map(Player::getUniqueId)
                .map(box.service()::getData)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach(SoulsData::save);*/
    }

    @Planned(ExecutionPhase.POST_STARTUP)
    private void whenStart(@Inject Box box) {
        //box.files().souls().soulList.forEach(soul -> soul.enable(box));
    }
}
