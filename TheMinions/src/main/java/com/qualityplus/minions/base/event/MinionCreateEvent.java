
package com.qualityplus.minions.base.event;

import com.qualityplus.assistant.api.event.HelperEvent;
import com.qualityplus.minions.persistance.data.MinionData;
import lombok.Getter;

@Getter
public final class MinionCreateEvent extends HelperEvent {
    private final MinionData minionData;

    public MinionCreateEvent(MinionData petData) {
        this.minionData = petData;
    }
}
