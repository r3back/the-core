package com.qualityplus.crafting.base.config;

import com.qualityplus.crafting.api.recipes.Recipes;
import com.qualityplus.crafting.base.recipes.CustomRecipe;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.*;
import eu.okaeri.platform.core.annotation.Configuration;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Configuration(path = "recipes.yml")
@Header("================================")
@Header("       Recipes      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class RecipesFile extends OkaeriConfig {
    public List<CustomRecipe> brewingRecipes = new ArrayList<>();

    public void saveRecipes(){
        brewingRecipes = new ArrayList<>(Recipes.values());
        save();
    }
}
