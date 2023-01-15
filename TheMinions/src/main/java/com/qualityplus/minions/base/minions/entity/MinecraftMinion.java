package com.qualityplus.minions.base.minions.entity;

import com.qualityplus.minions.api.minion.MinionEntity;
import com.qualityplus.minions.base.minions.entity.state.MinionState;
import com.qualityplus.minions.base.minions.minion.Minion;
import com.qualityplus.minions.base.minions.entity.handler.MinionHandlers;
import com.qualityplus.minions.base.minions.entity.handler.MinionHandlersImpl;
import com.qualityplus.minions.base.minions.entity.tracker.MinionEntityTracker;
import lombok.Getter;
import org.bukkit.Location;
import java.util.UUID;

public abstract class MinecraftMinion implements MinionEntity {
    protected final @Getter MinionState state;
    protected final MinionHandlers handlers;

    protected final Minion minion;

    protected MinecraftMinion(UUID minionUniqueId, UUID owner, Minion minion, boolean loaded) {
        this.state = new MinionState(minionUniqueId, owner, loaded);

        this.minion = minion;
        this.handlers = new MinionHandlersImpl(this, minion);
    }

    @Override
    public void spawn(Location location, boolean load) {
        state.setSpawn(location);

        MinionEntityTracker.registerNewEntity(this);
    }

    @Override
    public void deSpawn(DeSpawnReason deSpawnReason) {
        MinionEntityTracker.unregisterEntity(this);
    }

    @Override
    public UUID getMinionUniqueId() {
        return state.getUuid();
    }
}
