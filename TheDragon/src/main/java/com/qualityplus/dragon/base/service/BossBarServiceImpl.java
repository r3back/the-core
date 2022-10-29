package com.qualityplus.dragon.base.service;

import com.qualityplus.assistant.TheAssistantPlugin;
import com.qualityplus.assistant.api.util.IPlaceholder;
import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.assistant.util.placeholder.Placeholder;
import com.qualityplus.dragon.TheDragon;
import com.qualityplus.dragon.api.TheDragonAPI;
import com.qualityplus.dragon.api.game.dragon.TheDragonEntity;
import com.qualityplus.dragon.api.game.DragonGame;
import com.qualityplus.dragon.api.game.structure.type.DragonSpawn;
import com.qualityplus.dragon.api.service.*;
import com.qualityplus.dragon.base.configs.Messages;
import eu.okaeri.injector.annotation.Inject;
import com.qualityplus.dragon.base.game.player.EventPlayer;
import eu.okaeri.platform.core.annotation.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.stream.Collectors;

@Component
public final class BossBarServiceImpl implements BossBarService {
    private @Inject DragonGame dragonGame;
    private @Inject Messages messages;

    private Integer task;

    @Override
    public void start(){
        addTargetPlayers();
        task = Bukkit.getScheduler().scheduleAsyncRepeatingTask(TheDragon.getApi().getPlugin(), () -> {
            if (dragonGame.isActive())
                sendBossBar();
        }, 20, 20);
    }

    @Override
    public void stop(){
        if(task != null) Bukkit.getScheduler().cancelTask(task);

        TheAssistantPlugin.getAPI().getNms().sendBossBar(null, null);
    }

    private void sendBossBar(){
        TheDragonAPI api = TheDragon.getApi();

        TheDragonEntity theDragonEntity = api.getDragonService().getActiveDragon();
        int remaining = Optional.ofNullable(api.getGameService().getSwitchableEvent())
                .map(switchableEvent -> switchableEvent.getCurrentEvent().getRemainingTime())
                .orElse(0);

        List<IPlaceholder> placeholders = Arrays.asList(
                new Placeholder("thedragon_dragon_displayname", theDragonEntity.getDisplayName()),
                new Placeholder("thedragon_dragon_max_health", theDragonEntity.getMaxHealth()),
                new Placeholder("thedragon_dragon_health", theDragonEntity.getHealth()),
                new Placeholder("thedragon_next_event_remaining_time", remaining));

        String message = StringUtils.processMulti(messages.gameMessages.gameEventBossBar, placeholders);

        TheDragon.getApi().getUserService().getUsers().stream()
                .map(EventPlayer::getPlayer)
                .filter(Objects::nonNull)
                .forEach(player -> TheAssistantPlugin.getAPI().getNms().sendBossBar(player, message));
    }

    private void addTargetPlayers(){
        Optional<DragonSpawn> spawn = TheDragon.getApi().getStructureService().getSpawn();
        if(!spawn.isPresent()) return;
        getNearby(spawn.get().getLocation())
                .forEach(player -> TheDragon.getApi().getUserService().getUsers().add(new EventPlayer(player.getUniqueId(), 0)));
    }

    private Collection<Player> getNearby(Location location){
        return location.getWorld().getNearbyEntities(location, 100, 100, 100).stream()
                .filter(en -> en instanceof Player)
                .map(entity -> (Player) entity)
                .collect(Collectors.toList());
    }
}

