package com.qualityplus.alchemist.base.gui.individual;

import com.qualityplus.alchemist.api.box.Box;
import com.qualityplus.alchemist.base.gui.AlchemistGUI;
import com.qualityplus.alchemist.base.gui.recipes.RecipesGUI;
import com.qualityplus.alchemist.base.gui.select.SelectItemGUI;
import com.qualityplus.alchemist.base.recipes.BrewingRecipe;
import com.qualityplus.alchemist.util.AlchemistPlaceholderUtils;
import com.qualityplus.assistant.api.util.IPlaceholder;
import com.qualityplus.assistant.util.inventory.InventoryUtils;
import com.qualityplus.assistant.util.itemstack.ItemStackUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import com.qualityplus.alchemist.base.gui.select.SelectItemGUI.BrewingRecipeItem;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public final class IndividualRecipeGUI extends AlchemistGUI {
    private final IndividualRecipeGUIConfig config;
    private final BrewingRecipe brewingRecipe;

    public IndividualRecipeGUI(Box box, BrewingRecipe brewingRecipe) {
        super(box.files().inventories().individualRecipeGUIConfig, box);

        this.config = box.files().inventories().individualRecipeGUIConfig;
        this.brewingRecipe = brewingRecipe;
    }

    @Override
    public @NotNull Inventory getInventory() {
        InventoryUtils.fillInventory(inventory, config.getBackground());

        List<IPlaceholder> placeholders = AlchemistPlaceholderUtils.getRecipePlaceholders(brewingRecipe);

        setItem(config.getDurationItem(), placeholders);

        setItem(config.getInfoItem(), placeholders);

        inventory.setItem(config.getFuelItem().slot, ItemStackUtils.makeItem(config.getFuelItem(), placeholders, getIfNull(brewingRecipe.getFuel())));
        inventory.setItem(config.getOutputItem().slot, ItemStackUtils.makeItem(config.getOutputItem(), placeholders, getIfNull(brewingRecipe.getOutPut())));
        inventory.setItem(config.getInputItem().slot, ItemStackUtils.makeItem(config.getInputItem(), placeholders, getIfNull(brewingRecipe.getInput())));

        setItem(config.getCloseGUI());

        setItem(config.getBackPage());

        return inventory;
    }

    private ItemStack getIfNull(ItemStack itemStack){
        return Optional.ofNullable(itemStack).orElse(config.getEmptyItem().parseItem());
    }

    @Override
    public void onInventoryClick(InventoryClickEvent event) {
        event.setCancelled(true);

        if(!getTarget(event).equals(ClickTarget.INSIDE)) return;

        Player player = (Player) event.getWhoClicked();

        int slot = event.getSlot();

        if(isItem(slot, config.getCloseGUI())){
            player.closeInventory();
        }else if(isItem(slot, config.getInputItem())){
            if(event.isLeftClick())
                player.openInventory(new SelectItemGUI(box, brewingRecipe, player.getInventory().getContents(), BrewingRecipeItem.INPUT).getInventory());
            else{
                brewingRecipe.setInput(null);
                reopen(player);
            }
        }else if(isItem(slot, config.getFuelItem())){
            if(event.isLeftClick())
                player.openInventory(new SelectItemGUI(box, brewingRecipe, player.getInventory().getContents(), BrewingRecipeItem.FUEL).getInventory());
            else{
                brewingRecipe.setFuel(null);
                reopen(player);
            }
        }else if(isItem(slot, config.getOutputItem())){
            if(event.isLeftClick())
                player.openInventory(new SelectItemGUI(box, brewingRecipe, player.getInventory().getContents(), BrewingRecipeItem.OUTPUT).getInventory());
            else{
                brewingRecipe.setOutPut(null);
                reopen(player);
            }
        }else if(isItem(slot, config.getDurationItem())){
            int toChange = event.isShiftClick() ? 5 : 1;

            int duration = brewingRecipe.getTimer().getAmount();

            if(event.isLeftClick())
                brewingRecipe.getTimer().setAmount(Math.max(0, duration + toChange));
            else if(event.isRightClick())
                brewingRecipe.getTimer().setAmount(Math.max(0, duration - toChange));

            player.openInventory(new IndividualRecipeGUI(box, brewingRecipe).getInventory());
        }else if(isItem(slot, config.getBackPage())){
            player.openInventory(new RecipesGUI(box, 1).getInventory());
        }
    }

    private void reopen(Player player){
        player.openInventory(new IndividualRecipeGUI(box, brewingRecipe).getInventory());
    }
}
