package com.qualityplus.minions.listener;

import com.qualityplus.minions.api.box.Box;
import com.qualityplus.minions.api.minion.MinionEntity;
import com.qualityplus.minions.base.minions.minion.Minion;
import com.qualityplus.minions.base.minions.entity.factory.MinionEntityFactory;
import com.qualityplus.minions.base.minions.Minions;
import com.qualityplus.minions.base.minions.entity.tracker.MinionEntityTracker;
import com.qualityplus.minions.persistance.MinionsRepository;
import com.qualityplus.minions.persistance.data.MinionData;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Component;
import eu.okaeri.tasker.core.Tasker;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.Optional;

@Component
public final class MinionSaveListener implements Listener {
    private @Inject MinionsRepository repository;
    private @Inject Tasker tasker;
    private @Inject Box box;

    /*@EventHandler
    public void onQuit(PlayerQuitEvent event) {
        savePlayerData(event.getPlayer());
    }

    @EventHandler
    public void onQuit(PlayerKickEvent event) {
        savePlayerData(event.getPlayer());
    }*/

    private void savePlayerData(Player player){
        Optional<MinionData> data = box.getMinionsService().getData(player.getUniqueId());

        data.ifPresent(this::deSpawnPetItWasSpawned);
    }

    private void loadPetIfWasSpawned(MinionData data){

        if(data.getMinionId() == null) return;

        if(data.getLocation() == null) return;

        Minion pet = Minions.getByID(data.getMinionId());

        if(pet == null) return;

        MinionEntity petEntity = MinionEntityFactory.create(data.getUuid(), data.getUuid(), pet);

        petEntity.spawn(data.getLocation());
    }

    private void deSpawnPetItWasSpawned(MinionData data){

        Optional<MinionEntity> petEntity = MinionEntityTracker.getByID(data.getUuid());

        if(!petEntity.isPresent()) return;

        petEntity.get().deSpawn(MinionEntity.DeSpawnReason.SERVER_TURNED_OFF);
    }
}