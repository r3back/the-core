package com.qualityplus.minions.listener.persistence;

import com.qualityplus.minions.api.box.Box;
import com.qualityplus.minions.persistance.UserRepository;
import com.qualityplus.minions.persistance.data.UserData;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Component;
import eu.okaeri.tasker.core.Tasker;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Optional;

@Component
public final class UserListener implements Listener {
    private @Inject UserRepository repository;
    private @Inject Tasker tasker;
    private @Inject Box box;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        tasker.newChain()
                .async(() -> repository.get(event.getPlayer()))
                .acceptSync(box.getUserService()::addData)
                .acceptSync(this::loadPetIfWasSpawned)
                .execute();
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        savePlayerData(event.getPlayer());
    }

    @EventHandler
    public void onQuit(PlayerKickEvent event) {
        savePlayerData(event.getPlayer());
    }

    private void savePlayerData(Player player){
        Optional<UserData> data = box.getUserService().getData(player.getUniqueId());

        data.ifPresent(this::deSpawnPetItWasSpawned);
    }

    private void loadPetIfWasSpawned(UserData data){
        /*Optional<MinionData> petData = data.getSpawnedPetData();

        if(!petData.isPresent()) return;

        Pet pet = Pets.getByID(petData.get().getPetId());

        if(pet == null) return;

        PetEntity petEntity = PetEntityFactory.create(petData.get().getUuid(), data.getUuid(), pet);

        petEntity.spawn();*/
    }

    private void deSpawnPetItWasSpawned(UserData data){
        /*Optional<PetData> petData = data.getSpawnedPetData();

        if(!petData.isPresent()) return;

        Optional<PetEntity> petEntity = PetEntityTracker.getByID(petData.get().getUuid());

        if(!petEntity.isPresent()) return;

        petEntity.get().deSpawn(PetEntity.DeSpawnReason.SERVER_TURNED_OFF);*/
    }
}