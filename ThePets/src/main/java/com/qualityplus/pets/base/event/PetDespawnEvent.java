package com.qualityplus.pets.base.event;

import com.qualityplus.assistant.api.event.AssistantEvent;
import com.qualityplus.pets.persistance.data.PetData;
import lombok.Getter;

@Getter
public final class PetDespawnEvent extends AssistantEvent {
    private final PetData petData;

    public PetDespawnEvent(PetData petData) {
        this.petData = petData;
    }
}
