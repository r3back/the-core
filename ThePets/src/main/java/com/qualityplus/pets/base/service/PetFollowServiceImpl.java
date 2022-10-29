package com.qualityplus.pets.base.service;

import com.qualityplus.pets.api.pet.entity.PetEntity;
import com.qualityplus.pets.api.service.PetFollowService;
import eu.okaeri.platform.bukkit.annotation.Scheduled;
import eu.okaeri.platform.core.annotation.Component;
import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;

import java.util.Map;
import java.util.UUID;

@Component
public final class PetFollowServiceImpl implements PetFollowService{
    @Override
    public void follow(Map.Entry<UUID, PetEntity> entry){
        entry.getValue().followOwner();
    }
}
