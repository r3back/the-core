package com.qualityplus.pets.base.event;

import com.qualityplus.assistant.api.event.HelperEvent;
import com.qualityplus.pets.persistance.data.PetData;
import lombok.Getter;

@Getter
public final class PetLevelUPEvent extends HelperEvent {
    private final int newLevel;
    private final PetData data;

    public PetLevelUPEvent(PetData data, int newLevel) {
        this.newLevel = newLevel;
        this.data = data;
    }
}
