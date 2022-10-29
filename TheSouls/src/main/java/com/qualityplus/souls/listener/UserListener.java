package com.qualityplus.souls.listener;

import com.qualityplus.souls.api.box.Box;
import com.qualityplus.souls.persistance.data.SoulsData;
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
                .acceptSync(box.service()::addData)
                .execute();
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        this.tasker.newChain()
                .async(() -> box.service().getData(event.getPlayer().getUniqueId()))
                .acceptAsync((Consumer<Optional<SoulsData>>) user -> user.ifPresent(Document::save))
                .acceptAsync((Consumer<Optional<SoulsData>>) user -> user.ifPresent(box.service()::removeData))
                .execute();
    }
}