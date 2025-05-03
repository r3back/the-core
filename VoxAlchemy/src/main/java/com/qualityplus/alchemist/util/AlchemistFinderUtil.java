package com.qualityplus.alchemist.util;

import com.qualityplus.alchemist.api.recipes.Recipes;
import com.qualityplus.alchemist.base.gui.brewing.AlchemistStandGUIConfig;
import com.qualityplus.alchemist.base.recipes.BrewingRecipe;
import com.qualityplus.assistant.api.util.BukkitItemUtil;
import lombok.experimental.UtilityClass;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * Utilities to find recipes
 */
@UtilityClass
public class AlchemistFinderUtil {
    /**
     * Retrieves Brewing Recipe given a player's inventory
     *
     * @param inventory {@link Inventory}
     * @param config    {@link AlchemistStandGUIConfig}
     * @param player    {@link Player}
     * @return {@link BrewingRecipe}
     */
    public BrewingRecipe getAlchemyRecipe(final Inventory inventory, final AlchemistStandGUIConfig config, final Player player) {
        final ItemStack fuelItem = inventory.getItem(config.getFuelSlot());

        if (BukkitItemUtil.isNull(fuelItem)) {
            return null;
        }

        for (BrewingRecipe brewingRecipe : Recipes.values()) {
            if (!hasPermission(player, brewingRecipe)) {
                continue;
            }

            if (brewingRecipe.getFuel() != null && !brewingRecipe.getFuel().isSimilar(fuelItem)) {
                continue;
            }

            int equal = 0;
            int total = 0;
            for (final Integer slot : config.getInputSlots()) {
                final ItemStack inputItem = inventory.getItem(slot);

                if (BukkitItemUtil.isNull(inputItem)) {
                    continue;
                }

                if (inputItem.isSimilar(brewingRecipe.getInput())) {
                    equal++;
                } else {
                    total++;
                }
            }

            if (equal > 0 && total == 0) {
                return brewingRecipe;
            }
        }
        return null;
    }

    private boolean hasPermission(final Player player, final BrewingRecipe recipe) {
        return player != null && (recipe.getRecipePermission() != null && player.hasPermission(recipe.getRecipePermission()));
    }
}
