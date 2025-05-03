
package com.qualityplus.minions.base.event;

import com.qualityplus.assistant.api.event.AssistantEvent;
import com.qualityplus.minions.persistance.data.MinionData;
import lombok.Getter;

@Getter
public final class MinionCreateEvent extends AssistantEvent {
    private final MinionData minionData;

    public MinionCreateEvent(MinionData petData) {
        this.minionData = petData;
    }
}
