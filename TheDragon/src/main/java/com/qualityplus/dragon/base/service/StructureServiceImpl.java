package com.qualityplus.dragon.base.service;

import com.qualityplus.assistant.api.addons.paster.session.PasterSession;
import com.qualityplus.assistant.lib.eu.okaeri.injector.annotation.Inject;
import com.qualityplus.dragon.api.exception.InvalidSpawnException;
import com.qualityplus.dragon.api.game.structure.GameStructure;
import com.qualityplus.dragon.api.game.structure.GameStructures;
import com.qualityplus.dragon.api.game.structure.type.DragonAltar;
import com.qualityplus.dragon.api.game.structure.type.DragonCrystal;
import com.qualityplus.dragon.api.game.structure.type.DragonSpawn;
import com.qualityplus.dragon.api.service.StructureService;

import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Component;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Component
public final class StructureServiceImpl implements StructureService {
    private @Inject GameStructures gameStructures;

    @Override
    public void clearAltars() {
        gameStructures.clearAltars();
    }

    @Override
    public void spawnCrystals() {
        gameStructures.spawnCrystals();
    }

    @Override
    public CompletableFuture<PasterSession> pasteSchematic() throws InvalidSpawnException {
        return gameStructures.pasteSchematic();
    }

    @Override
    public boolean schematicExist() {
        return gameStructures.schematicExists();
    }

    @Override
    public void deSpawnCrystals() {
        gameStructures.deSpawnCrystals();
    }

    @Override
    public List<GameStructure> getStructures() {
        return gameStructures.getStructures();
    }

    @Override
    public List<DragonCrystal> getCrystals() {
        return gameStructures.getCrystals();
    }

    @Override
    public List<DragonAltar> getAltars() {
        return gameStructures.getAltars();
    }

    @Override
    public Optional<DragonCrystal> getCrystal(Location location) {
        return gameStructures.getCrystal(location);
    }

    @Override
    public Optional<DragonAltar> getAltar(Location location) {
        return gameStructures.getAltar(location);
    }

    @Override
    public Optional<DragonSpawn> getSpawn() {
        return gameStructures.getSpawn();
    }

    @Override
    public boolean isCrystal(Entity entity) {
        return gameStructures.isCrystal(entity);
    }

    @Override
    public boolean isAltar(Entity entity) {
        return gameStructures.isAltar(entity);
    }

    @Override
    public void addStructure(GameStructure structure) {
        gameStructures.addStructure(structure);
    }

    @Override
    public void removeStructure(GameStructure structure) {
        gameStructures.removeStructure(structure);
    }

}
