package com.qualityplus.minions.base.recipe;

import com.qualityplus.minions.api.recipe.Recipe;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

@Data
@AllArgsConstructor
public final class MinionRecipe implements Recipe {
    private final Map<Integer, ItemStack> ingredients;
    private final ItemStack result;
}
