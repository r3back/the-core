package com.qualityplus.pets.persistance.data;

import eu.okaeri.persistence.document.Document;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public final class UserData extends Document {
    private UUID uuid;
    private PetsData petsData = new PetsData();
    private SpawnedData spawnedData = new SpawnedData();
    private InventoryData inventoryData = new InventoryData();
    private UserSettings userSettings = new UserSettings();

    public void switchConvertToItemMode(){
        boolean convertToItemMode = userSettings.isConvertPetToItemMode();

        userSettings.setConvertPetToItemMode(!convertToItemMode);
    }

    public void switchPetsHiddenMode(){
        boolean petsAreHidden = userSettings.isPetsAreHidden();

        userSettings.setPetsAreHidden(!petsAreHidden);
    }
}
