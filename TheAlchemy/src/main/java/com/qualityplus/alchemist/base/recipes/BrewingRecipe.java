package com.qualityplus.alchemist.base.recipes;

import com.qualityplus.alchemist.api.recipes.Recipes;
import com.qualityplus.assistant.util.itemstack.ItemStackUtils;
import com.qualityplus.assistant.util.time.Timer;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Exclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.inventory.ItemStack;
import java.util.Optional;

@AllArgsConstructor
@Getter
@Setter
@Builder
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

    public BrewingRecipe(String id, String displayName, String description, Timer timer, ItemStack input, ItemStack outPut, ItemStack fuel, String recipePermission) {
        this.id = id;
        this.displayName = displayName;
        this.description = description;
        this.timer = timer;
        this.input = ItemStackUtils.serialize(input);
        this.outPut = ItemStackUtils.serialize(outPut);
        this.fuel = ItemStackUtils.serialize(fuel);
        this.recipePermission = recipePermission;
    }

    public ItemStack getInput() {
        if(inputItem == null) registerInput();
        return getCopy(inputItem);
    }

    public ItemStack getOutPut() {
        if(outPutItem == null) registerOutput();
        return getCopy(outPutItem);
    }

    public ItemStack getFuel() {
        if(fuelItem == null) registerFuel();
        return getCopy(fuelItem);
    }

    public void setInput(String input) {
        this.input = input;
        registerInput();
    }

    public void setOutPut(String outPut) {
        this.outPut = outPut;
        registerOutput();
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
        registerFuel();
    }

    private void registerInput(){
        this.inputItem = Optional.ofNullable(input).map(ItemStackUtils::deserialize).orElse(null);
    }

    private void registerOutput(){
        this.outPutItem = Optional.ofNullable(outPut).map(ItemStackUtils::deserialize).orElse(null);
    }

    private void registerFuel(){
        this.fuelItem = Optional.ofNullable(fuel).map(ItemStackUtils::deserialize).orElse(null);
    }

    private ItemStack getCopy(ItemStack itemStack){
        return Optional.ofNullable(itemStack).map(ItemStack::clone).orElse(null);
    }

    public void register(){
        Recipes.registerNewRecipe(this);
    }
}
