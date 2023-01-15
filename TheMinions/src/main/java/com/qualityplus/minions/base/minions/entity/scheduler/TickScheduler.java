package com.qualityplus.minions.base.minions.entity.scheduler;

import com.qualityplus.minions.base.minions.entity.tracker.MinionEntityTracker;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.bukkit.annotation.Scheduled;
import eu.okaeri.platform.core.annotation.Component;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.stream.Collectors;

@Component
public final class TickScheduler{
    @Inject
    private MinionTickService minionTickService;

    @Scheduled(rate = 1)
    private void tickAll(){
        MinionEntityTracker.TRACKED_ENTITIES.entrySet().forEach(minionTickService::tick);
    }

}