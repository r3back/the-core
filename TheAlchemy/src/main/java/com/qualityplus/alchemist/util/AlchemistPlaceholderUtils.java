package com.qualityplus.alchemist.util;

import com.qualityplus.alchemist.base.recipes.BrewingRecipe;
import com.qualityplus.assistant.api.util.BukkitItemUtil;
import com.qualityplus.assistant.api.util.IPlaceholder;
import com.qualityplus.assistant.util.placeholder.Placeholder;
import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.List;

@UtilityClass
public class AlchemistPlaceholderUtils {
    public List<IPlaceholder> getRecipePlaceholders(BrewingRecipe recipe){
        return Arrays.asList(
                new Placeholder("alchemist_recipe_displayname", recipe.getDisplayName()),
                new Placeholder("alchemist_recipe_description", recipe.getDescription()),
                new Placeholder("alchemist_recipe_id", recipe.getId()),
                new Placeholder("alchemist_recipe_permission", recipe.getRecipePermission()),
                new Placeholder("alchemist_recipe_duration", recipe.getTimer().getAmount()),
                new Placeholder("alchemist_recipe_fuel_item_displayname", emptyIfNull(BukkitItemUtil.getName(recipe.getFuel()))),
                new Placeholder("alchemist_recipe_fuel_item_lore", BukkitItemUtil.getItemLore(recipe.getFuel())),

                new Placeholder("alchemist_recipe_output_item_displayname", emptyIfNull(BukkitItemUtil.getName(recipe.getOutPut()))),
                new Placeholder("alchemist_recipe_output_item_lore", BukkitItemUtil.getItemLore(recipe.getOutPut())),

                new Placeholder("alchemist_recipe_input_item_displayname", emptyIfNull(BukkitItemUtil.getName(recipe.getInput()))),
                new Placeholder("alchemist_recipe_input_item_lore", BukkitItemUtil.getItemLore(recipe.getInput()))
        );
    }

    private String emptyIfNull(String input){
        return input == null || input.equals("") ? "&cEmpty" : input;
    }
}
