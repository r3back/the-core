package com.qualityplus.alchemist.api.recipes;

import com.google.common.collect.ImmutableSet;
import com.qualityplus.alchemist.api.box.Box;
import com.qualityplus.alchemist.base.recipes.BrewingRecipe;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.bukkit.annotation.Delayed;
import eu.okaeri.platform.core.annotation.Component;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public final class Recipes {
    private static final Map<String, BrewingRecipe> RECIPES_REGISTRY = new HashMap<>();
    private @Inject Box box;

    @ApiStatus.Internal
    public static void registerNewRecipe(@NotNull final BrewingRecipe recipe) {
        RECIPES_REGISTRY.put(recipe.getId().toLowerCase(), recipe);
    }

    @Nullable
    public static BrewingRecipe getByID(@NotNull final String id) {
        return RECIPES_REGISTRY.get(id.toLowerCase());
    }

    @Nullable
    public static BrewingRecipe getByKey(@NotNull final NamespacedKey key) {
        return RECIPES_REGISTRY.get(key.getKey());
    }

    @Nullable
    public static BrewingRecipe removeByID(@NotNull final String id) {
        return RECIPES_REGISTRY.remove(id.toLowerCase());
    }

    @Nullable
    public static BrewingRecipe removeByKey(@NotNull final NamespacedKey key) {
        return RECIPES_REGISTRY.remove(key.getKey());
    }

    public static Set<BrewingRecipe> values() {
        return ImmutableSet.copyOf(RECIPES_REGISTRY.values());
    }

    @Delayed(time = 1)
    public static void reloadRecipes(@Inject Box box){
        box.files().recipes().brewingRecipes.forEach(BrewingRecipe::register);
    }
}
