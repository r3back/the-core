package com.qualityplus.alchemist.base.event;

import com.qualityplus.alchemist.api.event.AlchemistEvent;
import com.qualityplus.alchemist.base.recipes.BrewingRecipe;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@Getter
public final class AlchemistBrewEvent extends AlchemistEvent {
    private final BrewingRecipe recipe;

    public AlchemistBrewEvent(@NotNull Player who, BrewingRecipe recipe) {
        super(who);

        this.recipe = recipe;
    }
}
