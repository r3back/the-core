package com.qualityplus.pets.base.pet.egg;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.pets.base.pet.Pet;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public final class PetEggEntity{
    private final UUID uuid;
    private final Pet pet;

    @Builder
    public PetEggEntity(UUID uuid, Pet pet) {
        this.uuid = uuid;
        this.pet = pet;
    }
}
