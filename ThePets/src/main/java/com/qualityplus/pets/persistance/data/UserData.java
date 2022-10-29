package com.qualityplus.pets.persistance.data;

import com.qualityplus.pets.api.pet.Pets;
import com.qualityplus.pets.base.pet.Pet;
import eu.okaeri.persistence.document.Document;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Optional;
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

    public Optional<Pet> getPet(){
        String id = spawnedData.getSpawnedPetId();

        if(id == null) return Optional.empty();

        Pet pet = Pets.getByID(id);

        return Optional.ofNullable(pet);
    }

    public String getPetName(){
        return getPet()
                .map(pet -> pet.getPetEgg().getDisplayName())
                .orElse(null);
    }
}
