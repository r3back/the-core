package com.qualityplus.pets.api.service;

import com.qualityplus.assistant.api.service.DataManagementService;
import com.qualityplus.pets.api.pet.entity.PetEntity;
import com.qualityplus.pets.base.pet.Pet;
import com.qualityplus.pets.persistance.data.UserData;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface UserPetService extends DataManagementService<UserData> {
    Set<UUID> getAllKeys();

    Optional<Pet> getSpawnedPet(UUID uuid);

    Optional<PetEntity> getSpawnedPetEntity(UUID uuid);

}
