package com.qualityplus.minions.base.recipe.provider;

import com.qualityplus.minions.api.recipe.Recipe;
import com.qualityplus.minions.api.recipe.provider.RecipeProvider;

public final class EmptyRecipeProviderImpl implements RecipeProvider {
    @Override
    public Recipe getRecipe(String id) {
        return null;
    }
}
