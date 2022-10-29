package com.qualityplus.dragon.base.service;

import com.qualityplus.dragon.TheDragon;
import com.qualityplus.dragon.api.game.dragon.TheDragonEntity;
import com.qualityplus.dragon.api.factory.DragonFactory;
import com.qualityplus.dragon.api.game.DragonGame;
import com.qualityplus.dragon.api.service.DragonService;
import com.qualityplus.dragon.api.game.structure.type.DragonSpawn;
import com.qualityplus.dragon.base.configs.DragonsFile;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Component;
import lombok.Getter;
import com.qualityplus.dragon.api.controller.DragonController;
import com.qualityplus.dragon.base.controller.DragonControllerImpl;
import org.bukkit.entity.EnderDragon;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Component
public final class DragonServiceImpl implements DragonService {
    private @Getter DragonController dragonController;
    private TheDragonEntity theDragonEntity;
    private @Inject DragonFactory factory;
    private @Inject DragonGame dragonGame;
    private @Inject DragonsFile dragons;

    @Override
    public void selectDragon() {
        this.theDragonEntity = factory.getDragon(dragons.dragonMap);

    }

    @Override
    public void spawnDragon() {
        Optional<DragonSpawn> spawn = TheDragon.getApi().getStructureService().getSpawn();

        theDragonEntity = factory.getDragon(dragons.dragonMap);

        if(theDragonEntity != null && spawn.isPresent())
            dragonController = new DragonControllerImpl(theDragonEntity.spawn(spawn.get().getLocation()));
    }

    @Override
    public CompletableFuture<Void> killDragon() {

        if(dragonController != null && dragonController.dragon() != null){
            dragonController.setAfk(true);
            dragonController.kill();
        }
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public TheDragonEntity getActiveDragon() {
        return theDragonEntity;
    }

    @Override
    public EnderDragon getActiveEnderDragon() {
        return Optional.ofNullable(dragonController)
                .map(DragonController::dragon)
                .orElse(null);
    }
}
