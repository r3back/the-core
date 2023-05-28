package com.qualityplus.alchemist.base.recipes;

import com.qualityplus.alchemist.api.recipes.Recipes;
import com.qualityplus.assistant.api.util.BukkitItemUtil;
import com.qualityplus.assistant.util.time.Timer;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Exclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;

/**
 * Brewing Recipe
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
public final class BrewingRecipe extends OkaeriConfig {
    private String id;
    private String displayName;
    private String description;
    private Timer timer;
    private String input;
    private String outPut;
    private String fuel;
    private String recipePermission;
    @Exclude
    private ItemStack inputItem;
    @Exclude
    private ItemStack outPutItem;
    @Exclude
    private ItemStack fuelItem;

    /**
     *
     * @param id               Recipe's Id
     * @param displayName      Recipe's Display name
     * @param description      Recipe's description
     * @param timer            {@link Timer}
     * @param input            {@link ItemStack}
     * @param outPut           {@link ItemStack}
     * @param fuel             {@link ItemStack}
     * @param recipePermission Recipes permission
     */
    public BrewingRecipe(final String id, final String displayName, final String description, final Timer timer, final ItemStack input,
                         final ItemStack outPut, final ItemStack fuel, final String recipePermission) {
        this.id = id;
        this.displayName = displayName;
        this.description = description;
        this.timer = timer;
        this.input = BukkitItemUtil.serialize(input);
        this.outPut = BukkitItemUtil.serialize(outPut);
        this.fuel = BukkitItemUtil.serialize(fuel);
        this.recipePermission = recipePermission;
    }

    /**
     * Retrieves input item
     *
     * @return {@link ItemStack}
     */
    public ItemStack getInput() {
        if (this.inputItem == null) {
            this.registerInput();
        }
        return getCopy(this.inputItem);
    }

    /**
     * Retrieves output item
     *
     * @return {@link ItemStack}
     */
    public ItemStack getOutPut() {
        if (this.outPutItem == null) {
            this.registerOutput();
        }
        return getCopy(this.outPutItem);
    }

    /**
     * Retrieves fuel item
     *
     * @return {@link ItemStack}
     */
    public ItemStack getFuel() {
        if (this.fuelItem == null) {
            this.registerFuel();
        }
        return this.getCopy(this.fuelItem);
    }

    /**
     *
     * @param input Input item as text
     */
    public void setInput(final String input) {
        this.input = input;
        this.registerInput();
    }

    /**
     *
     * @param outPut Output item as text
     */
    public void setOutPut(final String outPut) {
        this.outPut = outPut;
        this.registerOutput();
    }

    /**
     *
     * @param fuel Fuel item as text
     */
    public void setFuel(final String fuel) {
        this.fuel = fuel;
        registerFuel();
    }

    private void registerInput() {
        this.inputItem = Optional.ofNullable(this.input).map(BukkitItemUtil::deserialize).orElse(null);
    }

    private void registerOutput() {
        this.outPutItem = Optional.ofNullable(this.outPut).map(BukkitItemUtil::deserialize).orElse(null);
    }

    private void registerFuel() {
        this.fuelItem = Optional.ofNullable(this.fuel).map(BukkitItemUtil::deserialize).orElse(null);
    }

    private ItemStack getCopy(final ItemStack itemStack) {
        return Optional.ofNullable(itemStack).map(ItemStack::clone).orElse(null);
    }

    /**
     * Registers recipe
     */
    public void register() {
        Recipes.registerNewRecipe(this);
    }
}
