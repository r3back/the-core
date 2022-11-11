package com.qualityplus.pets.base.event;

import com.qualityplus.assistant.api.event.HelperEvent;
import com.qualityplus.assistant.api.event.PlayerHelperEvent;
import com.qualityplus.pets.persistance.data.PetData;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

@Getter
public final class PetCreateEvent extends HelperEvent {
    private final PetData petData;

    public PetCreateEvent(PetData petData) {
        this.petData = petData;
    }
}
