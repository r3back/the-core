package com.qualityplus.alchemist.base.gui.select;

import com.qualityplus.assistant.lib.com.cryptomorin.xseries.XSound;
import com.qualityplus.alchemist.api.box.Box;
import com.qualityplus.alchemist.base.gui.AlchemistGUI;
import com.qualityplus.alchemist.base.gui.individual.IndividualRecipeGUI;
import com.qualityplus.alchemist.base.recipes.BrewingRecipe;
import com.qualityplus.assistant.api.util.BukkitItemUtil;
import com.qualityplus.assistant.util.sound.SoundUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * Select Item GUI
 */
public final class SelectItemGUI extends AlchemistGUI {
    private final BrewingRecipeItem type;
    private final ItemStack[] itemStacks;
    private final BrewingRecipe recipe;

    /**
     *
     * @param box        {@link Box}
     * @param recipe     {@link BrewingRecipe}
     * @param itemStacks Array of {@link ItemStack}
     * @param type       {@link BrewingRecipeItem}
     */
    public SelectItemGUI(final Box box, final BrewingRecipe recipe, final ItemStack[] itemStacks, final BrewingRecipeItem type) {
        super(box.getFiles().inventories().getSelectItemGUIConfig(), box);

        this.itemStacks = itemStacks;
        this.recipe = recipe;
        this.type = type;
    }

    @NotNull
    @Override
    public Inventory getInventory() {

        this.inventory.setContents(this.itemStacks);

        return this.inventory;
    }

    @Override
    public void onInventoryClick(final InventoryClickEvent e) {
        e.setCancelled(true);

        if (BukkitItemUtil.isNull(e.getCurrentItem())) {
            return;
        }

        final Player player = (Player) e.getWhoClicked();

        final ItemStack itemStack = e.getCurrentItem().clone();

        final String parsed = BukkitItemUtil.serialize(itemStack);

        switch (this.type) {
            case FUEL:
                this.recipe.setFuel(parsed);
                break;
            case INPUT:
                this.recipe.setInput(parsed);
                break;
            case OUTPUT:
                this.recipe.setOutPut(parsed);
                break;
            default:
                break;
        }

        SoundUtils.playSound(player, XSound.ENTITY_EXPERIENCE_ORB_PICKUP);

        player.openInventory(new IndividualRecipeGUI(this.box, this.recipe).getInventory());
    }


    /**
     * Brewing Stand item types
     */
    public enum BrewingRecipeItem {
        /**
         * Represents the fuel item
         */
        FUEL,
        /**
         * Represents the output item
         */
        OUTPUT,
        /**
         * Represents the input item
         */
        INPUT;
    }
}
