package com.qualityplus.minions.base.minion.entity;

import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.assistant.util.math.MathUtils;
import com.qualityplus.minions.TheMinions;
import com.qualityplus.minions.api.minion.entity.MinionEntity;
import com.qualityplus.minions.base.minion.Minion;
import com.qualityplus.minions.base.minion.tracker.MinionEntityTracker;
import com.qualityplus.minions.persistance.data.MinionData;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class MinecraftMinion implements MinionEntity {
    protected @Getter final UUID petUniqueId;
    protected @Getter final UUID owner;

    protected final Minion minion;
    protected int count = 0;

    @Override
    public void spawn(Location location) {
        MinionEntityTracker.registerNewEntity(this);

        handleSpawn();
    }

    @Override
    public void deSpawn(DeSpawnReason deSpawnReason) {
        MinionEntityTracker.unregisterEntity(this);

        handleDeSpawnReason(deSpawnReason);
    }


    @Override
    public void update(){

    }


    private void handleSpawn() {
        /*OfflinePlayer offPlayer = getOffPlayer();

        Optional<UserData> data = ThePets.getApi().getUsersService().getData(offPlayer.getUniqueId());

        data.flatMap(userData -> Optional.ofNullable(userData.getSpawnedData())).ifPresent(spawnedData -> {
            spawnedData.setSpawnedPetUUID(petUniqueId);
            spawnedData.setSpawnedPetId(pet.getId());
        });*/
    }


    protected void handleDeSpawnReason(DeSpawnReason deSpawnReason){
        /*OfflinePlayer offPlayer = getOffPlayer();

        Optional<UserData> userData = ThePets.getApi().getUsersService().getData(offPlayer.getUniqueId());

        if(!deSpawnReason.equals(DeSpawnReason.PLAYER_DE_SPAWN_PET)) return;

        userData.ifPresent(data -> data.getSpawnedData().setSpawnedPetUUID(null));
        userData.ifPresent(data -> data.getSpawnedData().setSpawnedPetId(null));*/
    }

    private OfflinePlayer getOffPlayer(){
        return Bukkit.getOfflinePlayer(owner);
    }


    protected Player getPlayer(){
        return Bukkit.getPlayer(owner);
    }

    protected Location getNextLocation(){
        Player player = getPlayer();

        if(player == null || !player.isOnline()) return null;


        Vector vector = player.getEyeLocation().getDirection().clone().normalize().multiply(-1);

        vector.setY(Math.abs(vector.getY()));

        double x = Math.abs(vector.getX()) < 0.5 ? 0.5 : vector.getX();
        double z = Math.abs(vector.getZ()) < 0.5 ? 0.5 : vector.getZ();

        vector.setX(x);
        vector.setZ(z);

        Location location = player.getEyeLocation().clone().add(rotateAroundY(vector, Math.PI / 6));

        location.setY(location.getY() + MathUtils.sinCalc(count / (2 * Math.PI) * 0.5) * 0.15);

        count++;

        return location;
    }

    private Vector rotateAroundY(final Vector vector, final double angle) {
        double angleCos = Math.cos(angle);
        double angleSin = Math.sin(angle);
        double x = angleCos * vector.getX() + angleSin * vector.getZ();
        double z = -angleSin * vector.getX() + angleCos * vector.getZ();
        return vector.setX(x).setZ(z);
    }


    protected String getDisplayName(){
        String displayName = minion.getMinionEntityOptions().getDisplayName();

        /*List<IPlaceholder> placeholderList = PetPlaceholderUtil.getPetPlaceholders(petUniqueId)
                .with(PetPlaceholderUtil.getPetPlaceholders(pet))
                .with(new Placeholder("player", Bukkit.getOfflinePlayer(owner).getName())).get();*/

        return StringUtils.processMulti(displayName, /*placeholderList*/Collections.emptyList());
    }


    protected int getLevel(){
        Optional<MinionData> petData = TheMinions.getApi().getMinionsService().getData(petUniqueId);

        return petData.map(MinionData::getLevel).orElse(1);
    }
}
