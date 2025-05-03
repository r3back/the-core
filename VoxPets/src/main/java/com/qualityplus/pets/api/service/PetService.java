package com.qualityplus.pets.api.service;

import com.qualityplus.assistant.api.service.DataManagementService;
import com.qualityplus.pets.persistance.data.PetData;

public interface PetService extends DataManagementService<PetData> {
    void addXp(PetData petData, double xp);

    void saveAllData();

    int loadedAmount();
}
