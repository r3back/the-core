package com.qualityplus.dragon.base.events;

import com.qualityplus.dragon.VoxDragon;
import com.qualityplus.dragon.api.event.DragonGameEvent;
import com.qualityplus.dragon.api.game.DragonGame;
import com.qualityplus.dragon.base.configs.DragonEventsFile;
import org.bukkit.Bukkit;

public final class DragonFlyEvent extends DragonGameEvent {
    public DragonFlyEvent(final DragonEventsFile.SerializableEvent event) {
        super(event.generalSettings.secondsDuration, event.generalSettings.repeatEventAfterSeconds, event.generalSettings.dragonSpeedAmplifier, event.generalSettings.keepDragonAFK);
    }

    @Override
    public void start(final DragonGame dragonGame) {
        this.time = 0;

        this.task = Bukkit.getScheduler().runTaskTimerAsynchronously(VoxDragon.getApi().getPlugin(), () -> {

            if (this.time >= this.duration) {
                //Cancelling Event
                finish();
            }

            this.time++;
        }, 0, 20);
    }
}
