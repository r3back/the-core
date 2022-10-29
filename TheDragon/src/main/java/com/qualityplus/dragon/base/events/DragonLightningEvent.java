package com.qualityplus.dragon.base.events;

import com.qualityplus.dragon.TheDragon;
import com.qualityplus.dragon.api.event.DragonGameEvent;
import com.qualityplus.dragon.api.game.DragonGame;
import com.qualityplus.dragon.base.game.player.EventPlayer;
import com.qualityplus.dragon.base.configs.DragonEventsFile;
import org.bukkit.Bukkit;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.Objects;

public final class DragonLightningEvent extends DragonGameEvent {
    private final double lightningDamage;

    public DragonLightningEvent(DragonEventsFile.SerializableEvent event) {
        super(event.generalSettings.secondsDuration, event.generalSettings.repeatEventAfterSeconds, event.generalSettings.dragonSpeedAmplifier, event.generalSettings.keepDragonAFK);
        this.lightningDamage = event.lightningSettings.lightningDamage;
    }


    @Override
    public void start(DragonGame dragonGame) {
        time = 0;
        task = Bukkit.getScheduler().runTaskTimerAsynchronously(TheDragon.getApi().getPlugin(), () -> {
            //Cancelling Event
            if(time >= duration)
                finish();
            else
                //Check Event
                if(time % repeat == 0)
                    manageDragon(dragonGame);
            time+=1;
        }, 0, 20);
    }

    private void manageDragon(DragonGame dragonGame){
        makeLightnings(dragonGame);
    }

    private void makeLightnings(DragonGame dragonGame){
        TheDragon.getApi().getUserService().getUsers().stream()
                .map(EventPlayer::getPlayer)
                .filter(Objects::nonNull)
                .forEach(player -> makeLightning(player, dragonGame));
    }


    private void makeLightning(Player player, DragonGame dragonGame) {
        Bukkit.getScheduler().runTask(TheDragon.getApi().getPlugin(), () -> {
            if(dragonGame.isActive() && player != null && player.isOnline()) {
                LightningStrike ls = player.getLocation().getWorld().strikeLightning(player.getLocation());
                ls.setMetadata("LightningStrike", new FixedMetadataValue(TheDragon.getApi().getPlugin(), this.lightningDamage));
            }
        });
    }
}
