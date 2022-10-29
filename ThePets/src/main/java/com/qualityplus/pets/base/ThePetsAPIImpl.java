package com.qualityplus.pets.base;

import com.qualityplus.pets.api.ThePetsAPI;
import com.qualityplus.pets.api.service.PetsService;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Component;
import lombok.Getter;

@Component
public final class ThePetsAPIImpl implements ThePetsAPI {
    private @Getter @Inject PetsService petsService;
}
