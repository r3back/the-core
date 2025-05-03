package com.qualityplus.minions.api.recipe.provider;

import com.qualityplus.minions.api.recipe.Recipe;

public interface RecipeProvider {
    Recipe getRecipe(String id);
}
