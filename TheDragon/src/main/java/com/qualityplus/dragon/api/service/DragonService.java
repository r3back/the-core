package com.qualityplus.dragon.api.service;

import com.qualityplus.dragon.api.game.dragon.TheDragonEntity;
import com.qualityplus.dragon.api.controller.DragonController;
import org.bukkit.entity.EnderDragon;

import java.util.concurrent.CompletableFuture;

public interface DragonService {
    void selectDragon();
    void spawnDragon();
    CompletableFuture<Void> killDragon();

    TheDragonEntity getActiveDragon();
    EnderDragon getActiveEnderDragon();
    DragonController getDragonController();
}

