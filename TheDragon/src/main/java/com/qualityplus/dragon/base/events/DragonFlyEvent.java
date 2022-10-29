package com.qualityplus.dragon.base.events;

import com.qualityplus.dragon.TheDragon;
import com.qualityplus.dragon.api.event.DragonGameEvent;
import com.qualityplus.dragon.api.game.DragonGame;
import com.qualityplus.dragon.base.configs.DragonEventsFile;
import org.bukkit.Bukkit;

public final class DragonFlyEvent extends DragonGameEvent {

    public DragonFlyEvent(DragonEventsFile.SerializableEvent event) {
        super(event.generalSettings.secondsDuration, event.generalSettings.repeatEventAfterSeconds, event.generalSettings.dragonSpeedAmplifier, event.generalSettings.keepDragonAFK);
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
                    manageDragon();
            time++;
        }, 0, 20);
    }

    private void manageDragon(){

    }
}
