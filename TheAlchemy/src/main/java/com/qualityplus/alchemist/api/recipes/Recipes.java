package com.qualityplus.alchemist.api.recipes;

import com.google.common.collect.ImmutableSet;
import com.qualityplus.alchemist.api.box.Box;
import com.qualityplus.alchemist.base.recipes.BrewingRecipe;

import eu.okaeri.platform.bukkit.annotation.Delayed;
import eu.okaeri.platform.core.annotation.Component;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Recipes registry handler
 */
@Component
public final class Recipes {
    private static final Map<String, BrewingRecipe> RECIPES_REGISTRY = new HashMap<>();
    @Inject
    private Box box;

    /**
     * Register specific recipe
     *
     * @param recipe {@link BrewingRecipe}
     */
    @ApiStatus.Internal
    public static void registerNewRecipe(@NotNull final BrewingRecipe recipe) {
        RECIPES_REGISTRY.put(recipe.getId().toLowerCase(), recipe);
    }

    /**
     * Gets a recipe by id
     *
     * @param id Recipe id
     * @return {@link BrewingRecipe}
     */
    @Nullable
    public static BrewingRecipe getByID(@NotNull final String id) {
        return RECIPES_REGISTRY.get(id.toLowerCase());
    }

    /**
     * Gets a recipe by NameSpacedKey
     *
     * @param key {@link NamespacedKey}
     * @return {@link BrewingRecipe}
     */
    @Nullable
    public static BrewingRecipe getByKey(@NotNull final NamespacedKey key) {
        return RECIPES_REGISTRY.get(key.getKey());
    }

    /**
     * Removes a recipe by id
     *
     * @param id Recipe id
     * @return {@link BrewingRecipe}
     */
    @Nullable
    public static BrewingRecipe removeByID(@NotNull final String id) {
        return RECIPES_REGISTRY.remove(id.toLowerCase());
    }

    /**
     * Removes a recipe by NameSpacedKey
     *
     * @param key {@link NamespacedKey}
     * @return {@link BrewingRecipe}
     */
    @Nullable
    public static BrewingRecipe removeByKey(@NotNull final NamespacedKey key) {
        return RECIPES_REGISTRY.remove(key.getKey());
    }

    /**
     * Return all registered recipes
     *
     * @return Set of {@link BrewingRecipe}
     */
    public static Set<BrewingRecipe> values() {
        return ImmutableSet.copyOf(RECIPES_REGISTRY.values());
    }

    /**
     * Reload all recipes
     *
     * @param box {@link Box}
     */
    @Delayed(time = 1)
    public static void reloadRecipes(@Inject final Box box) {
        box.getFiles().recipes().getBrewingRecipes().forEach(BrewingRecipe::register);
    }
}
