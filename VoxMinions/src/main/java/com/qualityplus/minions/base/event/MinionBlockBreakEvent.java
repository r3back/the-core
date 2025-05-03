package com.qualityplus.minions.base.event;

import com.qualityplus.assistant.api.event.AssistantEvent;
import com.qualityplus.minions.api.minion.MinionEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bukkit.block.Block;

import java.util.concurrent.CompletableFuture;

@Data
@EqualsAndHashCode(callSuper = true)
public final class MinionBlockBreakEvent extends AssistantEvent {
    private final CompletableFuture<Void> future;
    private final MinionEntity minionEntity;
    private MinionAnimation animation;
    private final Block block;

    public MinionBlockBreakEvent(MinionEntity minionEntity, MinionAnimation animation, Block block, CompletableFuture<Void> future) {
        this.minionEntity = minionEntity;
        this.animation = animation;
        this.future = future;
        this.block = block;
    }

    public static enum MinionAnimation{
        BREAK,
        PLACE
    }
}
