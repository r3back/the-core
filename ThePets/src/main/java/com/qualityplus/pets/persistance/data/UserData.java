package com.qualityplus.pets.persistance.data;

import eu.okaeri.persistence.document.Document;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public final class UserData extends Document {
    private UUID uuid;
    private PetsData petsData;
    private SpawnedData spawnedData;
    private InventoryData inventoryData;
}
