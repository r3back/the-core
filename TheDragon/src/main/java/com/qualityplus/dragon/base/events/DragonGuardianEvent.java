package com.qualityplus.dragon.base.events;

import com.qualityplus.assistant.util.math.MathUtils;
import com.qualityplus.dragon.TheDragon;
import com.qualityplus.dragon.api.event.DragonGameEvent;
import com.qualityplus.dragon.api.factory.GuardianFactory;
import com.qualityplus.dragon.api.game.DragonGame;
import com.qualityplus.dragon.api.game.structure.type.DragonSpawn;
import com.qualityplus.dragon.base.configs.DragonEventsFile;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import com.qualityplus.dragon.base.configs.DragonEventsFile.GuardianChanceConfig;

import java.util.*;

public final class DragonGuardianEvent extends DragonGameEvent {
    private final List<GuardianChanceConfig> guardiansConfig;
    private final Set<Entity> guardians = new HashSet<>();
    private final int guardiansAmount;

    public DragonGuardianEvent(DragonEventsFile.SerializableEvent event) {
        super(event.generalSettings.secondsDuration, event.generalSettings.repeatEventAfterSeconds, event.generalSettings.dragonSpeedAmplifier, event.generalSettings.keepDragonAFK);
        this.guardiansAmount = event.guardianSettings.guardiansAmount;
        this.guardiansConfig = event.guardianSettings.guardians;
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
                //Chequear El Evento
                if(time % repeat == 0)
                    spawn();
            time++;
        }, 0, 20);
    }

    @Override
    public void finish() {
        super.finish();

        guardians.stream().filter(Objects::nonNull).forEach(Entity::remove);
    }


    private void spawn(){
        if(!isActive()) return;

        Set<Location> locations = getRandomLocations(guardiansAmount);

        GuardianFactory factory = TheDragon.getApi().getGuardianService().getFactory();

        locations.forEach(loc -> Bukkit.getScheduler().runTask(TheDragon.getApi().getPlugin(), () -> guardians.add(factory.getRandom(guardiansConfig).spawn(loc))));
    }

    private Set<Location> getRandomLocations(int amount){
        Set<Location> locations = new HashSet<>();
        Optional<DragonSpawn> dragonSpawn = TheDragon.getApi().getStructureService().getSpawn();
        if(!dragonSpawn.isPresent()) return locations;
        Location spawn = dragonSpawn.get().getLocation();
        for(int i = 0; locations.size()<amount; i++){
            Location location = spawn.clone();
            location.add(MathUtils.randomBetween(1, 11), 0, MathUtils.randomBetween(1, 11));
            location = location.getWorld().getHighestBlockAt(location).getLocation();
            location.add(0,1,0);
            locations.add(location);
            if(i == 100) break;
        }
        return locations;
    }
}
