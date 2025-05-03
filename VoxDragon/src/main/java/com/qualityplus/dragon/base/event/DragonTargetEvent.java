package com.qualityplus.dragon.base.event;

import com.qualityplus.dragon.base.controller.DragonControllerImpl;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public final class DragonTargetEvent extends Event {
    private final Player target;

    private final DragonControllerImpl dragon;

    private final DragonControllerImpl.TargetType type;

    public DragonTargetEvent(Player paramPlayer, DragonControllerImpl paramDragonSctructure, DragonControllerImpl.TargetType paramTargetType) {
        this.target = paramPlayer;
        this.dragon = paramDragonSctructure;
        this.type = paramTargetType;
    }

    public DragonControllerImpl.TargetType getType() {
        return this.type;
    }

    public Player getTarget() {
        return this.target;
    }

    public DragonControllerImpl getDragon() {
        return this.dragon;
    }

    public EnderDragon getEntity() {
        return this.dragon.dragon();
    }

    private static final HandlerList handlers = new HandlerList();

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
