package com.qualityplus.alchemist.base.gui.select;

import com.cryptomorin.xseries.XSound;
import com.qualityplus.alchemist.api.box.Box;
import com.qualityplus.alchemist.base.gui.AlchemistGUI;
import com.qualityplus.alchemist.base.gui.individual.IndividualRecipeGUI;
import com.qualityplus.alchemist.base.recipes.BrewingRecipe;
import com.qualityplus.assistant.util.itemstack.ItemStackUtils;
import com.qualityplus.assistant.util.sound.SoundUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public final class SelectItemGUI extends AlchemistGUI {
    private final BrewingRecipeItem type;
    private final ItemStack[] itemStacks;
    private final BrewingRecipe recipe;

    public SelectItemGUI(Box box, BrewingRecipe recipe, ItemStack[] itemStacks, BrewingRecipeItem type) {
        super(box.files().inventories().selectItemGUIConfig, box);

        this.itemStacks = itemStacks;
        this.recipe = recipe;
        this.type = type;
    }

    @NotNull
    @Override
    public Inventory getInventory() {

        inventory.setContents(itemStacks);

        return inventory;
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e) {
        e.setCancelled(true);

        if(ItemStackUtils.isNull(e.getCurrentItem())) return;

        Player player = (Player) e.getWhoClicked();

        ItemStack itemStack = e.getCurrentItem().clone();

        String parsed = ItemStackUtils.serialize(itemStack);

        switch (type) {
            case FUEL:
                recipe.setFuel(parsed);
                break;
            case INPUT:
                recipe.setInput(parsed);
                break;
            case OUTPUT:
                recipe.setOutPut(parsed);
                break;
        }

        SoundUtils.playSound(player, XSound.ENTITY_EXPERIENCE_ORB_PICKUP);

        player.openInventory(new IndividualRecipeGUI(box, recipe).getInventory());
    }

    public enum BrewingRecipeItem{
        FUEL,
        OUTPUT,
        INPUT
    }
}
