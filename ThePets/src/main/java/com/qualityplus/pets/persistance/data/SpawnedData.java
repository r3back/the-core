package com.qualityplus.pets.persistance.data;

import eu.okaeri.persistence.document.Document;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;


@Data
@EqualsAndHashCode(callSuper = true)
public final class SpawnedData extends Document{
    private String spawnedPetId;
    private UUID spawnedPetUUID;
    private boolean hidePets = false;
}
