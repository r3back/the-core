package com.qualityplus.pets.api.pet.entity;

import com.qualityplus.pets.base.pet.egg.PetEggEntity;
import org.bukkit.Location;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.UUID;

public interface PetEntity {
    UUID getOwner();
    void followOwner();
    UUID getPetUniqueId();
    void spawn();
    void deSpawn(DeSpawnReason reason);
    @Nullable
    Location getLocation();
    PetEggEntity getEgg();

    public static enum DeSpawnReason{
        SERVER_TURNED_OFF,
        PLAYER_DE_SPAWN_PET
    }
}
