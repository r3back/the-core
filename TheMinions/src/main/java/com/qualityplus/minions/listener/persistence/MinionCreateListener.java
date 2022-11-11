package com.qualityplus.minions.listener.persistence;

import com.qualityplus.minions.api.box.Box;
import com.qualityplus.minions.base.event.MinionCreateEvent;
import com.qualityplus.minions.persistance.MinionsRepository;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Component;
import eu.okaeri.tasker.core.Tasker;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

@Component
public final class MinionCreateListener implements Listener {
    private @Inject MinionsRepository repository;
    private @Inject Tasker tasker;
    private @Inject Box box;

    @EventHandler
    public void onJoin(MinionCreateEvent event) {
        this.tasker.newChain()
                .async(() -> repository.get(event.getMinionData()))
                .acceptAsync(repository::save)
                .acceptSync(box.getMinionsService()::addData)
                .execute();
    }
}