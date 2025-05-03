package com.qualityplus.pets.base;

import com.qualityplus.assistant.lib.eu.okaeri.injector.annotation.Inject;
import com.qualityplus.pets.api.VoxPetsAPI;
import com.qualityplus.pets.api.service.PetService;
import com.qualityplus.pets.api.service.UserPetService;
import com.qualityplus.pets.api.skills.DependencyHandler;

import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Component;
import lombok.Getter;
import org.bukkit.plugin.Plugin;

@Getter
@Component
public final class VoxPetsAPIImpl implements VoxPetsAPI {
    private @Inject DependencyHandler dependencyHandler;
    private @Inject UserPetService usersService;
    private @Inject PetService petsService;
    private @Inject Plugin plugin;
}
