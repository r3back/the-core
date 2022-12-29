
package com.qualityplus.minions.base.event;

import com.qualityplus.assistant.api.event.PlayerHelperEvent;
import com.qualityplus.minions.api.minion.MinionEntity;
import lombok.Getter;
import org.bukkit.entity.Player;

@Getter
public final class MinionInteractEvent extends PlayerHelperEvent {
    private final MinionEntity minionEntity;

    public MinionInteractEvent(Player player, MinionEntity minionEntity) {
        super(player);
        this.minionEntity = minionEntity;
    }
}
