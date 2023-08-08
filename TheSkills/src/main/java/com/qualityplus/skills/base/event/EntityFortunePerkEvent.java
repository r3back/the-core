package com.qualityplus.skills.base.event;

import com.qualityplus.skills.base.perk.Perk;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * Utility class for entity fortune perk
 */
@Getter
@Setter
public final class EntityFortunePerkEvent extends FortunePerkEvent {
    private Entity toDropEntity;

    /**
     * Makes entity fortune perk event
     *
     * @param who            {@link Player}
     * @param perk           {@link Perk}
     * @param toDropEntity   {@link Entity}
     * @param toDropLocation {@link Location}
     */
    public EntityFortunePerkEvent(@NotNull final Player who, final Perk perk, final Entity toDropEntity, final Location toDropLocation) {
        super(who, perk, toDropLocation);

        this.toDropEntity = toDropEntity;
    }
}
