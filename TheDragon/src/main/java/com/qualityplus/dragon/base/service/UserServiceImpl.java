package com.qualityplus.dragon.base.service;

import com.qualityplus.dragon.api.game.part.GameEnd;
import com.qualityplus.dragon.api.service.BossBarService;
import com.qualityplus.dragon.api.service.UserService;
import com.qualityplus.dragon.base.game.player.EventPlayer;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Component;
import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public final class UserServiceImpl implements UserService {
    //private final ReplyHandler<Player, String> messageHandler;
    private final List<EventPlayer> eventUsers = new ArrayList<>();
    private @Inject BossBarService bossBarManager;
    private @Inject GameEnd gameEnd;
    @Getter
    private UUID last;


    @Override
    public void sendMessage(EventPlayer eventUser, List<String> message) {
        if(eventUser.getPlayer() == null) return;
        message.forEach(msg -> eventUser.getPlayer().sendMessage(msg));
    }

    @Override
    public void sendMessage(List<String> message) {
        eventUsers.forEach(user -> sendMessage(user, message));
    }

    @Override
    public void resetData() {
        eventUsers.clear();
    }

    @Override
    public void addPlayerDamage(Player player, double damage) {
        eventUsers.stream()
                .filter(eventUser -> eventUser.getUuid().equals(player.getUniqueId()))
                .findFirst()
                .ifPresent(eventUser -> eventUser.addDamage(damage));
    }

    @Override
    public void removePlayersDamage(double damage) {
        eventUsers.forEach(eventUser -> eventUser.removeDamage(damage));
    }

    @Override
    public void setLast(UUID last) {
        this.last = last;
    }

    @Override
    public void startBossBar() {
        bossBarManager.start();
    }

    @Override
    public void stopBossBar() {
        bossBarManager.stop();
    }

    @Override
    public List<EventPlayer> getUsers() {
        return eventUsers;
    }

    @Override
    public Optional<EventPlayer> getByUUID(UUID uuid) {
        return eventUsers.stream().filter(eventPlayer -> eventPlayer.getUuid().equals(uuid)).findFirst();
    }

    @Override
    public void sendFinishMessage() {
        gameEnd.sendFinishMessage();
    }
}
