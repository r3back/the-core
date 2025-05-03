package com.qualityplus.skills.base.event;

import com.qualityplus.skills.base.perk.Perk;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
public final class EntityFortunePerkEvent extends FortunePerkEvent {
    private Entity toDropEntity;

    public EntityFortunePerkEvent(@NotNull Player who, Perk perk, Entity toDropEntity, Location toDropLocation) {
        super(who, perk, toDropLocation);

        this.toDropEntity = toDropEntity;
    }
}
