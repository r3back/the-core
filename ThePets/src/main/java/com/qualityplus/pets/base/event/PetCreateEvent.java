package com.qualityplus.pets.base.event;

import com.qualityplus.assistant.api.event.AssistantEvent;
import com.qualityplus.pets.persistance.data.PetData;
import lombok.Getter;

@Getter
public final class PetCreateEvent extends AssistantEvent {
    private final PetData petData;

    public PetCreateEvent(PetData petData) {
        this.petData = petData;
    }
}
