package com.qualityplus.minions.base.minions.entity.scheduler;

import com.qualityplus.minions.base.minions.entity.tracker.MinionEntityTracker;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.bukkit.annotation.Scheduled;
import eu.okaeri.platform.core.annotation.Component;

@Component
public final class TickScheduler{
    @Inject
    private MinionTickService minionTickService;

    @Scheduled(rate = 1, async = true)
    private void tickAll(){
        MinionEntityTracker.TRACKED_ENTITIES.entrySet().forEach(minionTickService::tick);
    }
}