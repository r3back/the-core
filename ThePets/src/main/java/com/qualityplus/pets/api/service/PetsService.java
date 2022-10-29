package com.qualityplus.pets.api.service;

import com.qualityplus.assistant.api.service.DataManagementService;
import com.qualityplus.pets.api.pet.entity.PetEntity;
import com.qualityplus.pets.base.pet.Pet;
import com.qualityplus.pets.persistance.data.UserData;
import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.UUID;

public interface PetsService extends DataManagementService<UserData> {
    Optional<Pet> getSpawnedPet(UUID uuid);

    Optional<PetEntity> getSpawnedPetEntity(UUID uuid);

}
