package com.qualityplus.pets.api;

import com.qualityplus.pets.api.service.PetService;
import com.qualityplus.pets.api.service.UserPetService;

public interface ThePetsAPI {
    UserPetService getUsersService();
    PetService getPetsService();
}
