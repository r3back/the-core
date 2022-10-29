package com.qualityplus.alchemist.base.gui.recipes;

import com.qualityplus.alchemist.api.box.Box;
import com.qualityplus.alchemist.base.gui.AlchemistGUI;
import com.qualityplus.alchemist.base.gui.individual.IndividualRecipeGUI;
import com.qualityplus.alchemist.base.recipes.BrewingRecipe;
import com.qualityplus.alchemist.api.recipes.Recipes;
import com.qualityplus.alchemist.util.AlchemistPlaceholderUtils;
import com.qualityplus.assistant.api.util.IPlaceholder;
import com.qualityplus.assistant.util.inventory.InventoryUtils;
import com.qualityplus.assistant.util.itemstack.ItemStackUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public final class RecipesGUI extends AlchemistGUI {
    private final Map<Integer, BrewingRecipe> recipeMap = new HashMap<>();
    private final RecipesGUIConfig config;

    public RecipesGUI(Box box, int page) {
        super(box.files().inventories().recipesGUIConfig, box);

        this.maxPerPage = 43;
        this.hasNext = Recipes.values().size() > maxPerPage * page;
        this.config = box.files().inventories().recipesGUIConfig;
        this.page = page;
    }

    @Override
    public @NotNull Inventory getInventory() {
        InventoryUtils.fillInventory(inventory, config.getBackground());

        List<BrewingRecipe> recipes = new ArrayList<>(Recipes.values());

        try {
            int slot = 0;
            int i = maxPerPage * (page - 1);
            if(recipes.size() > 0){
                while (slot < maxPerPage) {
                    if (recipes.size() > i && i >= 0) {
                        BrewingRecipe recipe = recipes.get(i);
                        List<IPlaceholder> placeholders = AlchemistPlaceholderUtils.getRecipePlaceholders(recipe);
                        inventory.setItem(slot, ItemStackUtils.makeItem(config.getRecipeItem(), placeholders));
                        recipeMap.put(slot, recipe);
                        slot++;
                        i++;
                        continue;
                    }
                    slot++;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        if(page > 1) setItem(config.getPreviousPage());

        if(hasNext) setItem(config.getNextPage());

        setItem(config.getCloseGUI());

        return inventory;
    }

    @Override
    public void onInventoryClick(InventoryClickEvent event) {
        event.setCancelled(true);

        if(!getTarget(event).equals(ClickTarget.INSIDE)) return;

        int slot = event.getSlot();

        Player player = (Player) event.getWhoClicked();

        if(isItem(slot, config.getCloseGUI())){
            player.closeInventory();
        }else if(isItem(slot, config.getNextPage()) && hasNext){
            player.openInventory(new RecipesGUI(box, page + 1).getInventory());
        }else if(isItem(slot, config.getPreviousPage()) && page > 1){
            player.openInventory(new RecipesGUI(box, page - 1).getInventory());
        }else if(recipeMap.containsKey(slot)){
            BrewingRecipe recipe = recipeMap.get(slot);

            if(recipe == null) return;

            player.openInventory(new IndividualRecipeGUI(box, recipe).getInventory());
        }
    }
}
