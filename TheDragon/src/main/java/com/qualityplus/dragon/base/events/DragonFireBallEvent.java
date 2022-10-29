package com.qualityplus.dragon.base.events;

import com.qualityplus.dragon.TheDragon;
import com.qualityplus.dragon.api.event.DragonGameEvent;
import com.qualityplus.dragon.api.game.DragonGame;
import com.qualityplus.dragon.api.handler.ParticleHandler;
import com.qualityplus.dragon.api.handler.ProjectileHandler;
import com.qualityplus.dragon.base.handler.ProjectileHandlerImpl;
import com.qualityplus.dragon.base.handler.ParticleHandlerImpl;
import com.qualityplus.dragon.base.configs.DragonEventsFile;
import org.bukkit.Bukkit;
import xyz.xenondevs.particle.ParticleEffect;

public final class DragonFireBallEvent extends DragonGameEvent {
    private final ProjectileHandler projectileEvent;
    private final ParticleHandler particleEvent;
    private final double amount;
    private final boolean particle = false;
    private final double damage = 0;

    public DragonFireBallEvent(DragonEventsFile.SerializableEvent event) {
        super(event.generalSettings.secondsDuration, event.generalSettings.repeatEventAfterSeconds, event.generalSettings.dragonSpeedAmplifier, event.generalSettings.keepDragonAFK);
        this.amount = event.dragonFireballSettings.dragonFireballsPerSecond;
        this.projectileEvent = new ProjectileHandlerImpl();
        this.particleEvent = new ParticleHandlerImpl();
    }

    @Override
    public void start(DragonGame dragonGame) {
        time = 0;
        task = Bukkit.getScheduler().runTaskTimerAsynchronously(TheDragon.getApi().getPlugin(), () -> {
            move(dragonGame);
            //Cancelling Event
            if(time >= duration)
                finish();
            else
                //Check Event
                if(time % repeat == 0)
                    projectileEvent.shoot(ProjectileHandler.ProjectileType.DRAGONBALL, damage, amount, dragonGame);

            if(particle) particleEvent.spell(dragonGame, ParticleEffect.SPELL_WITCH);

            time+=1;
        }, 0, 20);
    }
}
