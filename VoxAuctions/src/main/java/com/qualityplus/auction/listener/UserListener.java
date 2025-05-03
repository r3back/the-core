package com.qualityplus.auction.listener;

import com.qualityplus.assistant.lib.eu.okaeri.injector.annotation.Inject;
import com.qualityplus.auction.api.box.Box;
import com.qualityplus.auction.persistence.data.User;

import com.qualityplus.assistant.lib.eu.okaeri.persistence.document.Document;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Component;
import com.qualityplus.assistant.lib.eu.okaeri.tasker.core.Tasker;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * Utility class for player join event
 */
@Component
public final class UserListener implements Listener {
    private @Inject Tasker tasker;
    private @Inject Box box;

    /**
     * Makes a join player
     *
     * @param event {@link PlayerJoinEvent}
     */
    @EventHandler
    public void onJoin(final PlayerJoinEvent event) {
        this.tasker.newChain()
                .async(() -> this.box.repository().get(event.getPlayer()))
                .acceptSync(this.box.service()::addUser)
                .execute();
    }

    /**
     * makes a player quit
     *
     * @param event {@link PlayerQuitEvent}
     */
    @EventHandler
    public void onQuit(final PlayerQuitEvent event) {
        this.tasker.newChain()
                .async(() -> this.box.service().getUser(event.getPlayer().getUniqueId()))
                .acceptAsync((Consumer<Optional<User>>) user -> user.ifPresent(Document::save))
                .acceptAsync((Consumer<Optional<User>>) user -> user.ifPresent(this.box.service()::removeUser))
                .execute();
    }
}
