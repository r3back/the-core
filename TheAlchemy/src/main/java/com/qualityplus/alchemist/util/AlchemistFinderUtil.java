package com.qualityplus.alchemist.util;

import com.qualityplus.alchemist.base.gui.brewing.AlchemistStandGUIConfig;
import com.qualityplus.alchemist.base.recipes.BrewingRecipe;
import com.qualityplus.alchemist.api.recipes.Recipes;
import com.qualityplus.assistant.util.itemstack.ItemStackUtils;
import lombok.experimental.UtilityClass;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@UtilityClass
public class AlchemistFinderUtil {
    public BrewingRecipe getAlchemyRecipe(Inventory inventory, AlchemistStandGUIConfig config, Player player){
        ItemStack fuelItem = inventory.getItem(config.getFuelSlot());

        if(ItemStackUtils.isNull(fuelItem)) return null;

        for(BrewingRecipe brewingRecipe : Recipes.values()){
            if(!hasPermission(player, brewingRecipe)) continue;

            if(brewingRecipe.getFuel() != null && !brewingRecipe.getFuel().isSimilar(fuelItem)) continue;

            int equal = 0;
            int total = 0;
            for(Integer slot : config.getInputSlots()){
                ItemStack inputItem = inventory.getItem(slot);
                if(ItemStackUtils.isNull(inputItem)) continue;
                if(inputItem.isSimilar(brewingRecipe.getInput()))
                    equal++;
                else total++;
            }

            if(equal > 0 && total == 0) return brewingRecipe;
        }
        return null;
    }

    private boolean hasPermission(Player player, BrewingRecipe recipe){
        return player != null && (recipe.getRecipePermission() != null && player.hasPermission(recipe.getRecipePermission()));
    }
}
