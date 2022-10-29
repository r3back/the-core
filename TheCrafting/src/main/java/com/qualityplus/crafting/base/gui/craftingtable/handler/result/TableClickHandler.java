package com.qualityplus.crafting.base.gui.craftingtable.handler.result;

import com.qualityplus.assistant.util.inventory.InventoryUtils;
import com.qualityplus.assistant.util.itemstack.ItemStackUtils;
import com.qualityplus.assistant.util.placeholder.Placeholder;
import com.qualityplus.assistant.util.placeholder.PlaceholderBuilder;
import com.qualityplus.crafting.api.box.Box;
import com.qualityplus.crafting.api.recipes.IRecipe;
import com.qualityplus.crafting.base.gui.craftingtable.CraftingTableGUIConfig;
import com.qualityplus.crafting.base.recipes.CustomRecipe;
import com.qualityplus.crafting.util.CraftingFinderUtil;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public final class TableClickHandler {
    //Fake Slot | Special Slot
    private final Map<Integer, IRecipe> automaticRecipes = new HashMap<>();
    private final Map<Integer, Integer> tableRelationSlots;
    private final NormalResultHandler normalHandler;
    private final ShiftResultHandler shiftHandler;
    private final CraftingTableGUIConfig config;
    private final Inventory inventory;
    private final InventoryView view;
    private final Box box;

    public TableClickHandler(Inventory inventory, InventoryView view, Box box, Map<Integer, Integer> slots) {
        this.normalHandler = new NormalResultHandler(slots, this, inventory, view);
        this.shiftHandler = new ShiftResultHandler(slots, this, inventory, view);
        this.config = box.files().inventories().getCraftingGui();
        this.tableRelationSlots = slots;
        this.inventory = inventory;
        this.view = view;
        this.box = box;
    }

    public void handleClick(Player player){
        Bukkit.getScheduler().runTask(box.plugin(), () -> {
            moveItemsToFakeTable();
            checkRecipe(player);
            checkAutomaticCrafts(player);
        });
    }

    public void handleResultClick(InventoryClickEvent event, boolean autoRecipe){
        Player player = (Player) event.getWhoClicked();

        event.setCancelled(true);

        IRecipe automaticRecipe = automaticRecipes.getOrDefault(event.getSlot(), null);

        if(!event.isShiftClick()){
            normalHandler.handle(player, event, automaticRecipe, autoRecipe);
        }else{
            shiftHandler.handle(player, event.getCurrentItem(), automaticRecipe, autoRecipe);
        }

    }

    /*
    Chequea si hay recipe y si hay pone los items
     */
    private void checkRecipe(Player player){
        Bukkit.getScheduler().runTask(box.plugin(), () -> inventory.setItem(config.getResultSlot(), getItem(player, CraftingFinderUtil.getCraftingRecipe(inventory, view, tableRelationSlots))));
    }

    private void checkAutomaticCrafts(Player player){
        InventoryUtils.fillInventory(inventory, config.getBackground());

        automaticRecipes.clear();

        List<CustomRecipe> recipeList = CraftingFinderUtil.getCraftingRecipes(player, config.getAutoRecipeSlots().size());

        if(recipeList.size() <= 0) return;

        Bukkit.getScheduler().runTask(box.plugin(), () -> {
            int i = 0;
            for(CustomRecipe recipe : recipeList){
                inventory.setItem(config.getAutoRecipeSlots().get(i), getItem(player, recipe));
                automaticRecipes.put(config.getAutoRecipeSlots().get(i), recipe);
                i++;
            }
        });
    }

    private ItemStack getItem(Player player, IRecipe recipe){

        if(recipe != null){
            PlaceholderBuilder placeholderList = PlaceholderBuilder.create(
                    new Placeholder("crafting_recipe_result_item_displayname", ItemStackUtils.getName(recipe.getResult())),
                    new Placeholder("crafting_recipe_result_item_lore", ItemStackUtils.getItemLore(recipe.getResult()))
            );

            if(recipe instanceof CustomRecipe && !((CustomRecipe) recipe).hasPermission(player)) {
                placeholderList.with(new Placeholder("crafting_recipe_status_placeholder", box.files().messages().recipeMessages.noPermissionPlaceholder));
                return ItemStackUtils.makeItem(config.getResultItemEmpty(), placeholderList.get());
            }

            return ItemStackUtils.makeItem(config.getResultItemFilled(), placeholderList.get(), recipe.getResult().clone());
        }else{
            return ItemStackUtils.makeItem(config.getResultItemEmpty(), Collections.singletonList(
                            new Placeholder("crafting_recipe_status_placeholder", box.files().messages().recipeMessages.recipeNotFoundPlaceholder)
            ));
        }

    }

    /*
    Mueve los items de la mesa special al fake inventory
     */
    private void moveItemsToFakeTable(){
        tableRelationSlots.forEach((key, value) -> view.setItem(key, inventory.getItem(value)));
    }
}
