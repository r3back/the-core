package com.qualityplus.pets.base.event;

import com.qualityplus.assistant.api.event.HelperEvent;
import com.qualityplus.pets.persistance.data.PetData;
import lombok.Getter;

@Getter
public final class PetCreateEvent extends HelperEvent {
    private final PetData petData;

    public PetCreateEvent(PetData petData) {
        this.petData = petData;
    }
}
