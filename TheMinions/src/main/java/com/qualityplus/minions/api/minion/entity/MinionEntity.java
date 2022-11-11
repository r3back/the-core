package com.qualityplus.minions.api.minion.entity;

import org.bukkit.Location;

import java.util.UUID;

public interface MinionEntity {
    UUID getOwner();
    void tickMinion();
    UUID getPetUniqueId();
    void spawn(Location location);
    void deSpawn(DeSpawnReason reason);
    void update();


    public static enum DeSpawnReason{
        SERVER_TURNED_OFF,
        PLAYER_DE_SPAWN_PET
    }
}
