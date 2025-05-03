package com.qualityplus.crafting.base.event;

import com.qualityplus.crafting.api.event.AlchemistEvent;
import com.qualityplus.crafting.base.recipes.CustomRecipe;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@Getter
public final class VoxCraftingCraftEvent extends AlchemistEvent {
    private final CustomRecipe recipe;

    public VoxCraftingCraftEvent(@NotNull Player who, CustomRecipe recipe) {
        super(who);

        this.recipe = recipe;
    }
}
