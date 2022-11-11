package com.qualityplus.pets.base.event;

import com.qualityplus.assistant.api.event.HelperEvent;
import com.qualityplus.pets.persistance.data.PetData;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class PetXPGainEvent extends HelperEvent {
    private final PetData data;
    private double xp;

    public PetXPGainEvent(PetData data, double xp) {
        this.data = data;
        this.xp = xp;
    }
}
