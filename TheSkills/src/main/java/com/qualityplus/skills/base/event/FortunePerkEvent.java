package com.qualityplus.skills.base.event;

import com.qualityplus.skills.api.event.PerkEvent;
import com.qualityplus.skills.base.perk.Perk;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * Utility class for fortune perk
 */
@Getter
@Setter
public abstract class FortunePerkEvent extends PerkEvent {
    private Location toDropLocation;

    /**
     * Makes a fortune perk
     *
     * @param who             {@link Player}
     * @param perk            {@link Perk}
     * @param toDropLocation  {@link Location}
     */
    public FortunePerkEvent(@NotNull final Player who, final Perk perk, final Location toDropLocation) {
        super(who, perk);

        this.toDropLocation = toDropLocation;
    }
}
