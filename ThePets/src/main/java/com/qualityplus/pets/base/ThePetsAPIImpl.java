package com.qualityplus.pets.base;

import com.qualityplus.pets.api.ThePetsAPI;
import com.qualityplus.pets.api.service.PetService;
import com.qualityplus.pets.api.service.UserPetService;
import com.qualityplus.pets.api.skills.DependencyHandler;

import eu.okaeri.platform.core.annotation.Component;
import lombok.Getter;

@Component
public final class ThePetsAPIImpl implements ThePetsAPI {
    private @Getter @Inject DependencyHandler dependencyHandler;
    private @Getter @Inject UserPetService usersService;
    private @Getter @Inject PetService petsService;
}
