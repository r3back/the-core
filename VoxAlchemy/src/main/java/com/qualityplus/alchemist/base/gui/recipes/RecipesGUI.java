package com.qualityplus.alchemist.base.gui.recipes;

import com.qualityplus.alchemist.api.box.Box;
import com.qualityplus.alchemist.api.recipes.Recipes;
import com.qualityplus.alchemist.base.gui.AlchemistGUI;
import com.qualityplus.alchemist.base.gui.individual.IndividualRecipeGUI;
import com.qualityplus.alchemist.base.recipes.BrewingRecipe;
import com.qualityplus.alchemist.util.AlchemistPlaceholderUtils;
import com.qualityplus.assistant.api.util.IPlaceholder;
import com.qualityplus.assistant.util.inventory.InventoryUtils;
import com.qualityplus.assistant.util.itemstack.ItemStackUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Recipes GUI
 */
public final class RecipesGUI extends AlchemistGUI {
    private final Map<Integer, BrewingRecipe> recipeMap = new HashMap<>();
    private final RecipesGUIConfig config;

    /**
     *
     * @param box  {@link Box}
     * @param page Gui Page number
     */
    public RecipesGUI(final Box box, final int page) {
        super(box.getFiles().inventories().getRecipesGUIConfig(), box);

        this.maxPerPage = 43;
        this.hasNext = Recipes.values().size() > maxPerPage * page;
        this.config = box.getFiles().inventories().getRecipesGUIConfig();
        this.page = page;
    }

    @Override
    public @NotNull Inventory getInventory() {
        InventoryUtils.fillInventory(this.inventory, this.config.getBackground());

        final List<BrewingRecipe> recipes = new ArrayList<>(Recipes.values());

        try {
            int slot = 0;
            int i = this.maxPerPage * (this.page - 1);
            if (recipes.size() > 0) {
                while (slot < this.maxPerPage) {
                    if (recipes.size() > i && i >= 0) {
                        final BrewingRecipe recipe = recipes.get(i);
                        final List<IPlaceholder> placeholders = AlchemistPlaceholderUtils.getRecipePlaceholders(recipe);
                        this.inventory.setItem(slot, ItemStackUtils.makeItem(this.config.getRecipeItem(), placeholders));
                        this.recipeMap.put(slot, recipe);
                        slot++;
                        i++;
                        continue;
                    }
                    slot++;
                }
            }
        } catch (final IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        if (this.page > 1) {
            setItem(this.config.getPreviousPage());
        }

        if (this.hasNext) {
            setItem(this.config.getNextPage());
        }

        setItem(this.config.getCloseGUI());

        return this.inventory;
    }

    @Override
    public void onInventoryClick(final InventoryClickEvent event) {
        event.setCancelled(true);

        if (!getTarget(event).equals(ClickTarget.INSIDE)) {
            return;
        }

        final int slot = event.getSlot();

        final Player player = (Player) event.getWhoClicked();

        if (isItem(slot, this.config.getCloseGUI())) {
            player.closeInventory();
        } else if (isItem(slot, this.config.getNextPage()) && this.hasNext) {
            player.openInventory(new RecipesGUI(this.box, this.page + 1).getInventory());
        } else if (isItem(slot, this.config.getPreviousPage()) && this.page > 1) {
            player.openInventory(new RecipesGUI(this.box, this.page - 1).getInventory());
        } else if (this.recipeMap.containsKey(slot)) {
            final BrewingRecipe recipe = this.recipeMap.get(slot);

            if (recipe == null) {
                return;
            }

            player.openInventory(new IndividualRecipeGUI(box, recipe).getInventory());
        }
    }
}
