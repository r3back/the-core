package com.qualityplus.crafting.base.event;

import com.qualityplus.crafting.api.event.AlchemistEvent;
import com.qualityplus.crafting.base.recipes.CustomRecipe;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@Getter
public final class TheCraftingCraftEvent extends AlchemistEvent {
    private final CustomRecipe recipe;

    public TheCraftingCraftEvent(@NotNull Player who, CustomRecipe recipe) {
        super(who);

        this.recipe = recipe;
    }
}
