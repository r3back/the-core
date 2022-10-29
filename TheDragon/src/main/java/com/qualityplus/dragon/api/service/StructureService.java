package com.qualityplus.dragon.api.service;

import com.qualityplus.assistant.api.addons.paster.session.PasterSession;
import com.qualityplus.dragon.api.game.structure.GameStructure;
import com.qualityplus.dragon.api.game.structure.type.DragonAltar;
import com.qualityplus.dragon.api.game.structure.type.DragonCrystal;
import com.qualityplus.dragon.api.game.structure.type.DragonSpawn;
import com.qualityplus.dragon.api.exception.InvalidSpawnException;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface StructureService {
    void clearAltars();
    void spawnCrystals();
    void deSpawnCrystals();

    CompletableFuture<PasterSession> pasteSchematic() throws InvalidSpawnException;

    boolean schematicExist();

    List<DragonCrystal> getCrystals();
    List<GameStructure> getStructures();
    List<DragonAltar> getAltars();

    Optional<DragonCrystal> getCrystal(Location location);
    Optional<DragonAltar> getAltar(Location location);
    Optional<DragonSpawn> getSpawn();

    boolean isCrystal(Entity entity);
    boolean isAltar(Entity entity);

    void addStructure(GameStructure structure);
    void removeStructure(GameStructure structure);
}
