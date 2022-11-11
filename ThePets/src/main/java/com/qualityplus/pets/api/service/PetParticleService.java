package com.qualityplus.pets.api.service;

import com.qualityplus.pets.api.pet.entity.PetEntity;

import java.util.Map;
import java.util.UUID;

public interface PetParticleService {
    void spellParticle(Map.Entry<UUID, PetEntity> entry);
}
