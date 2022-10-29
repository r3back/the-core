package com.qualityplus.pets.base.pet.entity;

import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.assistant.util.itemstack.ItemBuilder;
import com.qualityplus.pets.ThePets;
import com.qualityplus.pets.api.pet.Pets;
import com.qualityplus.pets.api.pet.entity.PetEntity;
import com.qualityplus.pets.base.pet.Pet;
import com.qualityplus.pets.base.pet.egg.PetEggEntity;
import com.qualityplus.pets.base.pet.tracker.PetEntityTracker;
import com.qualityplus.pets.persistance.data.SpawnedData;
import com.qualityplus.pets.persistance.data.UserData;
import com.qualityplus.pets.util.PetEggUtil;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class ArmorStandPet implements PetEntity {
    private ArmorStand armorStand;
    @Getter
    private final UUID petUniqueId;
    @Getter
    private final UUID owner;

    private final Pet pet;

    public static ArmorStandPet create(Player player, PetEggEntity petEgg){
        return new ArmorStandPet(petEgg.getUuid(), player.getUniqueId(), petEgg.getPet());
    }

    /*public static ArmorStandPet create(UserData userData){
        SpawnedData spawnedData = userData.getSpawnedData();

        Pet pet = Pets.getByID(spawnedData.getSpawnedPetId());

        return new ArmorStandPet(spawnedData.getSpawnReason(), spawnedData.getSpawnedPetUUID(), userData.getUuid(), pet);
    }*/

    @Override
    public void spawn() {
        PetEntityTracker.registerNewEntity(this);

        Optional.ofNullable(getNextLocation())
                .ifPresent(this::createArmorStand);

        handleSpawn();
    }

    @Override
    public void deSpawn(DeSpawnReason deSpawnReason) {
        PetEntityTracker.unregisterEntity(this);

        Optional.ofNullable(armorStand)
                .filter(ArmorStandPet::entityIsValid)
                .ifPresent(Entity::remove);

        handleDeSpawnReason(deSpawnReason);
    }

    @Nullable
    @Override
    public Location getLocation(){
        return Optional.ofNullable(armorStand)
                .filter(entity -> !entity.isDead())
                .map(Entity::getLocation)
                .orElse(null);
    }

    @Override
    public PetEggEntity getEgg() {
        return new PetEggEntity(petUniqueId, pet);
    }


    @Override
    public void followOwner() {
        Optional.ofNullable(getNextLocation())
                .filter(location -> entityIsValid(armorStand))
                .ifPresent(armorStand::teleport);
    }

    private void handleSpawn() {
        OfflinePlayer offPlayer = getOffPlayer();

        Optional<UserData> data = ThePets.getApi().getPetsService().getData(offPlayer.getUniqueId());

        data.ifPresent(userData -> userData.getSpawnedData().setSpawnedPetUUID(petUniqueId));
        data.ifPresent(userData -> userData.getSpawnedData().setSpawnedPetId(pet.getId()));
    }


    private void handleDeSpawnReason(DeSpawnReason deSpawnReason){
        OfflinePlayer offPlayer = getOffPlayer();

        Optional<UserData> userData = ThePets.getApi().getPetsService().getData(offPlayer.getUniqueId());

        if(deSpawnReason.equals(DeSpawnReason.PLAYER_DE_SPAWN_PET)){
            userData.ifPresent(data -> data.getInventoryData().addInventoryPet(petUniqueId, pet.getId()));
            userData.ifPresent(data -> data.getSpawnedData().setSpawnedPetUUID(null));
            userData.ifPresent(data -> data.getSpawnedData().setSpawnedPetId(null));
        }
    }

    private OfflinePlayer getOffPlayer(){
        return Bukkit.getOfflinePlayer(owner);
    }

    private Player getPlayer(){
        return Bukkit.getPlayer(owner);
    }

    private Location getNextLocation(){
        Player player = getPlayer();

        if(player == null || !player.isOnline()) return null;

        Vector offset = player.getEyeLocation().getDirection().clone().normalize()
                .multiply(-1);

        offset.add(new Vector(Math.abs(offset.getX()) < 0.5 ? 0.5 : 0, 0, 0));
        offset.add(new Vector(0,0, Math.abs(offset.getZ()) < 0.5 ? 0.5 : 0));
        offset.add(new Vector(0,Math.abs(offset.getY()),0));
        offset.rotateAroundY(Math.PI / 6);

        return player.getEyeLocation().clone().add(offset);
    }

    private static boolean entityIsValid(ArmorStand armorStand){
        return armorStand != null && !armorStand.isDead();
    }

    private void createArmorStand(Location location){

        armorStand = location.getWorld().spawn(location, ArmorStand.class);

        armorStand.setSmall(true);
        armorStand.setVisible(false);
        armorStand.setInvulnerable(true);
        armorStand.setGravity(false);
        armorStand.setCollidable(false);
        armorStand.setCustomNameVisible(true);
        armorStand.setCustomName(StringUtils.color(pet.getPetEntityOptions().getDisplayName()));
        armorStand.setHelmet(ItemBuilder.of()
                        .amount(1)
                        .displayName("")
                        .lore(new ArrayList<>())
                        .material(pet.getPetEntityOptions().getMaterial())
                        .headData(pet.getPetEntityOptions().getTexture())
                        .buildStack());
    }
}
