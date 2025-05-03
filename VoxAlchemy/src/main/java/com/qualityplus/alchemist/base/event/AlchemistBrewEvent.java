package com.qualityplus.alchemist.base.event;

import com.qualityplus.alchemist.api.event.AlchemistEvent;
import com.qualityplus.alchemist.base.recipes.BrewingRecipe;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * Event called when Brewing stand is used
 */
@Getter
public final class AlchemistBrewEvent extends AlchemistEvent {
    private final BrewingRecipe recipe;

    /**
     *
     * @param who    {@link Player}
     * @param recipe {@link BrewingRecipe}
     */
    public AlchemistBrewEvent(@NotNull final Player who, final BrewingRecipe recipe) {
        super(who);

        this.recipe = recipe;
    }
}
