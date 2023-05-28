package com.qualityplus.alchemist.base.gui.individual;

import com.qualityplus.alchemist.api.box.Box;
import com.qualityplus.alchemist.base.gui.AlchemistGUI;
import com.qualityplus.alchemist.base.gui.recipes.RecipesGUI;
import com.qualityplus.alchemist.base.gui.select.SelectItemGUI;
import com.qualityplus.alchemist.base.gui.select.SelectItemGUI.BrewingRecipeItem;
import com.qualityplus.alchemist.base.recipes.BrewingRecipe;
import com.qualityplus.alchemist.util.AlchemistPlaceholderUtils;
import com.qualityplus.assistant.api.util.IPlaceholder;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.util.inventory.InventoryUtils;
import com.qualityplus.assistant.util.itemstack.ItemStackUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

/**
 * Individual Recipes GUI
 */
public final class IndividualRecipeGUI extends AlchemistGUI {
    private final IndividualRecipeGUIConfig config;
    private final BrewingRecipe brewingRecipe;

    /**
     *
     * @param box           {@link Box}
     * @param brewingRecipe {@link BrewingRecipe}
     */
    public IndividualRecipeGUI(final Box box, final BrewingRecipe brewingRecipe) {
        super(box.getFiles().inventories().getIndividualRecipeGUIConfig(), box);

        this.config = box.getFiles().inventories().getIndividualRecipeGUIConfig();
        this.brewingRecipe = brewingRecipe;
    }

    @Override
    public @NotNull Inventory getInventory() {
        InventoryUtils.fillInventory(this.inventory, this.config.getBackground());

        final List<IPlaceholder> placeholders = AlchemistPlaceholderUtils.getRecipePlaceholders(this.brewingRecipe);

        setItem(this.config.getDurationItem(), placeholders);

        setItem(this.config.getInfoItem(), placeholders);

        final Item fuel = this.config.getFuelItem();
        final Item output = this.config.getOutputItem();
        final Item input = this.config.getInputItem();

        this.inventory.setItem(fuel.slot, ItemStackUtils.makeItem(fuel, placeholders, getIfNull(this.brewingRecipe.getFuel())));
        this.inventory.setItem(output.slot, ItemStackUtils.makeItem(output, placeholders, getIfNull(this.brewingRecipe.getOutPut())));
        this.inventory.setItem(input.slot, ItemStackUtils.makeItem(input, placeholders, getIfNull(this.brewingRecipe.getInput())));

        setItem(this.config.getCloseGUI());

        setItem(this.config.getBackPage());

        return this.inventory;
    }

    private ItemStack getIfNull(final ItemStack itemStack) {
        return Optional.ofNullable(itemStack).orElse(this.config.getEmptyItem().parseItem());
    }

    @Override
    public void onInventoryClick(final InventoryClickEvent event) {
        event.setCancelled(true);

        if (!getTarget(event).equals(ClickTarget.INSIDE)) {
            return;
        }

        final Player player = (Player) event.getWhoClicked();

        final int slot = event.getSlot();

        if (isItem(slot, this.config.getCloseGUI())) {
            player.closeInventory();
        } else if (isItem(slot, this.config.getInputItem())) {
            if (event.isLeftClick()) {
                player.openInventory(new SelectItemGUI(box, this.brewingRecipe, player.getInventory().getContents(), BrewingRecipeItem.INPUT).getInventory());
            } else {
                this.brewingRecipe.setInput(null);
                reopen(player);
            }
        } else if (isItem(slot, this.config.getFuelItem())) {
            if (event.isLeftClick()) {
                player.openInventory(new SelectItemGUI(box, this.brewingRecipe, player.getInventory().getContents(), BrewingRecipeItem.FUEL).getInventory());
            } else {
                this.brewingRecipe.setFuel(null);
                reopen(player);
            }
        } else if (isItem(slot, this.config.getOutputItem())) {
            if (event.isLeftClick()) {
                player.openInventory(new SelectItemGUI(box, this.brewingRecipe, player.getInventory().getContents(), BrewingRecipeItem.OUTPUT).getInventory());
            } else {
                this.brewingRecipe.setOutPut(null);
                reopen(player);
            }
        } else if (isItem(slot, this.config.getDurationItem())) {
            final int toChange = event.isShiftClick() ? 5 : 1;

            final int duration = this.brewingRecipe.getTimer().getAmount();

            if (event.isLeftClick()) {
                this.brewingRecipe.getTimer().setAmount(Math.max(0, duration + toChange));
            } else if (event.isRightClick()) {
                this.brewingRecipe.getTimer().setAmount(Math.max(0, duration - toChange));
            }

            player.openInventory(new IndividualRecipeGUI(this.box, this.brewingRecipe).getInventory());
        } else if (isItem(slot, this.config.getBackPage())) {
            player.openInventory(new RecipesGUI(this.box, 1).getInventory());
        }
    }

    private void reopen(final Player player) {
        player.openInventory(new IndividualRecipeGUI(this.box, this.brewingRecipe).getInventory());
    }
}
