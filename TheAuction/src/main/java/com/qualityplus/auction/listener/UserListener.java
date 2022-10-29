package com.qualityplus.auction.listener;

import com.qualityplus.auction.api.box.Box;
import com.qualityplus.auction.persistence.data.User;
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
    private @Inject Tasker tasker;
    private @Inject Box box;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        this.tasker.newChain()
                .async(() -> box.repository().get(event.getPlayer()))
                .acceptSync(box.service()::addUser)
                .execute();
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        this.tasker.newChain()
                .async(() -> box.service().getUser(event.getPlayer().getUniqueId()))
                .acceptAsync((Consumer<Optional<User>>) user -> user.ifPresent(Document::save))
                .acceptAsync((Consumer<Optional<User>>) user -> user.ifPresent(box.service()::removeUser))
                .execute();
    }
}