package com.qualityplus.pets.api.service;

import com.qualityplus.pets.api.pet.entity.PetEntity;
import com.qualityplus.pets.base.pet.Pet;
import com.qualityplus.pets.persistance.data.UserData;

import java.util.Optional;
import java.util.UUID;

public interface PetsService {
    Optional<UserData> getData(UUID uuid);

    void addData(UserData data);

    void removeData(UserData data);

    Optional<Pet> getSpawnedPet(UUID uuid);

    Optional<PetEntity> getSpawnedPetEntity(UUID uuid);

}
