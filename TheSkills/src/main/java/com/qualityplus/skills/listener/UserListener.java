package com.qualityplus.skills.listener;

import com.qualityplus.assistant.lib.eu.okaeri.injector.annotation.Inject;
import com.qualityplus.skills.api.box.Box;
import com.qualityplus.skills.persistance.SkillsRepository;
import com.qualityplus.skills.persistance.data.UserData;

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
 * Utility class for user events
 */
@Component
public final class UserListener implements Listener {
    private @Inject SkillsRepository repository;
    private @Inject Tasker tasker;
    private @Inject Box box;


    /**
     * Adds a player on join
     *
     * @param event {@link PlayerJoinEvent}
     */
    @EventHandler
    public void onJoin(final PlayerJoinEvent event) {
        this.tasker.newChain()
                .async(() -> this.repository.get(event.getPlayer()))
                .acceptSync(this.box.service()::addData)
                .execute();
    }

    /**
     * Adds a player quit event
     *
     * @param event {@link PlayerJoinEvent}
     */
    @EventHandler
    public void onQuit(final PlayerQuitEvent event) {
        this.tasker.newChain()
                .async(() -> this.box.service().getData(event.getPlayer().getUniqueId()))
                .acceptAsync((Consumer<Optional<UserData>>) user -> user.ifPresent(Document::save))
                .acceptAsync((Consumer<Optional<UserData>>) user -> user.ifPresent(this.box.service()::removeData))
                .execute();
    }
}
