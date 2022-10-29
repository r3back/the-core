package com.qualityplus.pets.listener;

import com.qualityplus.pets.api.box.Box;
import com.qualityplus.pets.persistance.PetsRepository;
import com.qualityplus.pets.persistance.data.UserData;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.persistence.document.Document;
import eu.okaeri.platform.core.annotation.Component;
import eu.okaeri.tasker.core.Tasker;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Optional;
import java.util.function.Consumer;

@Component
public final class UserListener implements Listener {
    private @Inject PetsRepository repository;
    private @Inject Tasker tasker;
    private @Inject Box box;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        this.tasker.newChain()
                .async(() -> repository.get(event.getPlayer()))
                .acceptSync(box.service()::addData)
                .execute();
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        this.tasker.newChain()
                .async(() -> box.service().getData(event.getPlayer().getUniqueId()))
                .acceptAsync((Consumer<Optional<UserData>>) user -> user.ifPresent(Document::save))
                .acceptAsync((Consumer<Optional<UserData>>) user -> user.ifPresent(box.service()::removeData))
                .execute();
    }
}