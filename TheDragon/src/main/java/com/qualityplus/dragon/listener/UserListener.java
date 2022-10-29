package com.qualityplus.dragon.listener;

import com.qualityplus.dragon.api.service.UserDBService;
import com.qualityplus.dragon.persistance.DragonRepository;
import com.qualityplus.dragon.persistance.data.UserData;
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
    private @Inject DragonRepository repository;
    private @Inject UserDBService service;
    private @Inject Tasker tasker;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        this.tasker.newChain()
                .async(() -> repository.get(event.getPlayer()))
                .acceptSync(service::addData)
                .execute();
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        this.tasker.newChain()
                .async(() -> service.getDragonData(event.getPlayer().getUniqueId()))
                .acceptAsync((Consumer<Optional<UserData>>) user -> user.ifPresent(Document::save))
                .acceptAsync((Consumer<Optional<UserData>>) user -> user.ifPresent(service::removeData))
                .execute();
    }
}
