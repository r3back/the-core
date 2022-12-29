package com.qualityplus.crafting.base.gui.craftingtable.handler.result;

import com.qualityplus.assistant.api.util.BukkitItemUtil;
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
import org.bukkit.scheduler.BukkitScheduler;

import java.util.*;

@RequiredArgsConstructor
public final class TableClickHandler {
    //Fake Slot | Special Slot
    private final Map<Integer, IRecipe> automaticRecipes = new HashMap<>();
    private final BukkitScheduler scheduler = Bukkit.getScheduler();
    private final Map<Integer, Integer> tableRelationSlots;
    private final NormalResultHandler normalHandler;
    private final ShiftResultHandler shiftHandler;
    private final CraftingTableGUIConfig config;
    private final Inventory inventory;
    private final InventoryView view;
    private final Box box;

    public TableClickHandler(Inventory inventory, InventoryView view, Box box, Map<Integer, Integer> slots) {
        this.normalHandler = new NormalResultHandler(slots, this, inventory, view, box);
        this.shiftHandler = new ShiftResultHandler(slots, this, inventory, view, box);
        this.config = box.files().inventories().getCraftingGui();
        this.tableRelationSlots = slots;
        this.inventory = inventory;
        this.view = view;
        this.box = box;
    }

    public void handleClick(Player player){
        scheduler.runTask(box.plugin(), () -> {
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
        int slot = config.getResultSlot();

        IRecipe recipe = CraftingFinderUtil.getCraftingRecipe(inventory, view, tableRelationSlots);

        ItemStack item = getItem(player, recipe);

        Bukkit.getScheduler().runTask(box.plugin(), () -> inventory.setItem(slot, item));
    }

    private void checkAutomaticCrafts(Player player){
        InventoryUtils.fillInventory(inventory, config.getBackground());

        automaticRecipes.clear();

        List<CustomRecipe> recipeList = CraftingFinderUtil.getCraftingRecipes(player, config.getAutoRecipeSlots().size());

        if(recipeList.size() <= 0) return;

        scheduler.runTask(box.plugin(), () -> {
            int i = 0;
            for(CustomRecipe recipe : recipeList){
                if(box.files().blockedCraftings().isBlocked(recipe)) continue;

                int slot = config.getAutoRecipeSlots().get(i);

                inventory.setItem(slot, getItem(player, recipe));

                automaticRecipes.put(slot, recipe);
                i++;
            }
        });
    }

    private ItemStack getItem(Player player, IRecipe recipe){

        if(recipe != null){
            PlaceholderBuilder placeholderList = PlaceholderBuilder.create(
                    new Placeholder("crafting_recipe_result_item_displayname", BukkitItemUtil.getName(recipe.getResult())),
                    new Placeholder("crafting_recipe_result_item_lore", BukkitItemUtil.getItemLore(recipe.getResult()))
            );

            if(box.files().blockedCraftings().isBlocked(recipe)){
                placeholderList.with(new Placeholder("crafting_recipe_status_placeholder", Optional
                        .ofNullable(box.files().messages().recipeMessages.blockedCraftingPlaceholder)
                        .orElse("&cThis recipe is disabled!")));
                return ItemStackUtils.makeItem(config.getResultItemEmpty(), placeholderList.get());
            }

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
