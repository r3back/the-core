package com.qualityplus.pets.listener.persistence;

import com.qualityplus.pets.api.box.Box;
import com.qualityplus.pets.base.event.PetCreateEvent;
import com.qualityplus.pets.persistance.PetRepository;

import eu.okaeri.platform.core.annotation.Component;
import eu.okaeri.tasker.core.Tasker;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

@Component
public final class PetCreateListener implements Listener {
    private @Inject PetRepository repository;
    private @Inject Tasker tasker;
    private @Inject Box box;

    @EventHandler
    public void onJoin(PetCreateEvent event) {
        this.tasker.newChain()
                .async(() -> repository.get(event.getPetData()))
                .acceptAsync(repository::save)
                .acceptSync(box.petService()::addData)
                .execute();
    }
}