package com.qualityplus.minions.base.minions.entity.state;

import com.qualityplus.assistant.api.gui.FakeInventory;
import com.qualityplus.minions.base.minions.entity.MinionStorageState;
import com.qualityplus.minions.base.minions.entity.status.MinionStatus;
import lombok.*;
import org.bukkit.Location;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public final class MinionState {
    private MinionStorageState storageState;
    private FakeInventory fakeInventory;
    private MinionStatus oldStatus;
    private MinionStatus status;
    private long lastActionTime;
    private boolean isBreaking;
    private boolean isSelling;
    private final UUID owner;
    private final UUID uuid;
    private boolean loaded;
    private Location spawn;

    public MinionState(UUID uuid, UUID owner, boolean loaded) {
        this.lastActionTime = System.currentTimeMillis();
        this.status = MinionStatus.INVALID_LAYOUT;
        this.isBreaking = true;
        this.isSelling = false;
        this.loaded = loaded;
        this.owner = owner;
        this.uuid = uuid;
    }
}
