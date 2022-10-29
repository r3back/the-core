package com.qualityplus.pets.api.service;

import com.qualityplus.pets.api.pet.entity.PetEntity;

import java.util.Map;
import java.util.UUID;

public interface PetFollowService {
    void follow(Map.Entry<UUID, PetEntity> entry);
}
