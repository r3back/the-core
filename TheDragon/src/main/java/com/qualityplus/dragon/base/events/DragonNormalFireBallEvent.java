package com.qualityplus.dragon.base.events;

import com.qualityplus.dragon.TheDragon;
import com.qualityplus.dragon.api.event.DragonGameEvent;
import com.qualityplus.dragon.api.game.DragonGame;
import com.qualityplus.dragon.api.handler.ParticleHandler;
import com.qualityplus.dragon.api.handler.ProjectileHandler;
import com.qualityplus.dragon.api.handler.ProjectileHandler.ProjectileType;
import com.qualityplus.dragon.base.configs.DragonEventsFile;
import com.qualityplus.dragon.base.handler.ParticleHandlerImpl;
import com.qualityplus.dragon.base.handler.ProjectileHandlerImpl;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import xyz.xenondevs.particle.ParticleEffect;

public final class DragonNormalFireBallEvent extends DragonGameEvent {
    private final ProjectileHandler projectileEvent;
    private final ParticleHandler particleEvent;
    private final double fireballsPerSecond;
    private final boolean showParticle;
    private final double fireballDamage;

    public DragonNormalFireBallEvent(DragonEventsFile.SerializableEvent event) {
        super(event.generalSettings.secondsDuration, event.generalSettings.repeatEventAfterSeconds, event.generalSettings.dragonSpeedAmplifier, event.generalSettings.keepDragonAFK);
        this.fireballsPerSecond = event.fireBallsSettings.fireballsPerSecond;
        this.fireballDamage = event.fireBallsSettings.fireballDamage;
        this.showParticle = event.fireBallsSettings.showParticle;
        this.projectileEvent = new ProjectileHandlerImpl();
        this.particleEvent = new ParticleHandlerImpl();
    }

    @Override
    public void start(DragonGame dragonGame) {
        Plugin plugin = TheDragon.getApi().getPlugin();
        time = 0;

        task = Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, () -> {
            move(dragonGame);
            //Cancelling Event
            if(time >= duration)
                finish();
            else
                //Check Event
                if(time % repeat == 0)
                    projectileEvent.shoot(ProjectileType.FIREBALL, fireballDamage, fireballsPerSecond, dragonGame);

            if(showParticle) {
                /**
                 * TODO
                 */
                particleEvent.spell(dragonGame, ParticleEffect.FLAME);
            }

            time+=1;
        }, 0, 20);
    }
}
