package com.qualityplus.minions.api.recipe;

import org.bukkit.inventory.ItemStack;

import java.util.Map;

public interface Recipe {
    Map<Integer, ItemStack> getIngredients();

    ItemStack getResult();
}
