package com.qualityplus.pets.base.service;

import com.qualityplus.pets.api.pet.Pets;
import com.qualityplus.pets.api.pet.entity.PetEntity;
import com.qualityplus.pets.api.service.PetsService;
import com.qualityplus.pets.base.pet.Pet;
import com.qualityplus.pets.base.pet.tracker.PetEntityTracker;
import com.qualityplus.pets.persistance.data.UserData;
import eu.okaeri.platform.core.annotation.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Component
public final class PetsServiceImpl implements PetsService {
    private final Map<UUID, UserData> dataMap = new HashMap<>();

    @Override
    public Optional<UserData> getData(UUID uuid) {
        return Optional.ofNullable(dataMap.getOrDefault(uuid, null));
    }

    @Override
    public void addData(UserData data) {
        dataMap.put(data.getUuid(), data);
    }

    @Override
    public void removeData(UserData data) {
        dataMap.remove(data.getUuid());
    }

    @Override
    public Optional<Pet> getSpawnedPet(UUID uuid) {
        UserData data = dataMap.get(uuid);

        return data.getSpawnedData().getSpawnedPetId() == null ? Optional.empty() : Optional.ofNullable(Pets.getByID(data.getSpawnedData().getSpawnedPetId()));
    }

    @Override
    public Optional<PetEntity> getSpawnedPetEntity(UUID uuid) {
        return PetEntityTracker.getByID(uuid);
    }
}
