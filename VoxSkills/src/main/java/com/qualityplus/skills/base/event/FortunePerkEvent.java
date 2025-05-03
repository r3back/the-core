package com.qualityplus.skills.base.event;

import com.qualityplus.skills.api.event.PerkEvent;
import com.qualityplus.skills.base.perk.Perk;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
public abstract class FortunePerkEvent extends PerkEvent {
    private Location toDropLocation;

    public FortunePerkEvent(@NotNull Player who, Perk perk, Location toDropLocation) {
        super(who, perk);

        this.toDropLocation = toDropLocation;
    }
}
