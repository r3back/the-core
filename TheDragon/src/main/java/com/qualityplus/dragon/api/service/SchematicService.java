package com.qualityplus.dragon.api.service;

import com.qualityplus.assistant.api.addons.paster.schematic.Schematic;

import java.util.Optional;

public interface SchematicService {
    void load();

    Optional<Schematic> getSchematic(String name);

    boolean schematicExist();
}
