package com.qualityplus.crafting.base.recipes;

import com.qualityplus.assistant.util.map.MapUtils;
import com.qualityplus.crafting.api.recipes.IRecipe;
import com.qualityplus.crafting.api.recipes.Recipes;
import com.qualityplus.assistant.util.itemstack.ItemStackUtils;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Exclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Getter
@Setter
@Builder
public final class CustomRecipe extends OkaeriConfig implements IRecipe {
    private String id;
    private String displayName;
    private String resultSerialized;
    private String recipePermission;
    private String category;
    private Map<Integer, String> ingredientsSerialized;
    private int slot;
    private int page;

    @Exclude
    private Map<Integer, ItemStack> ingredients;
    @Exclude
    private ItemStack result;

    @Builder
    public CustomRecipe(String id, String displayName, String resultSerialized, Map<Integer, String> ingredientsSerialized, String recipePermission, String category) {
        this.id = id;
        this.category = category;
        this.displayName = displayName;
        this.resultSerialized = resultSerialized;
        this.recipePermission = recipePermission;
        this.ingredientsSerialized = ingredientsSerialized;
    }

    public ItemStack getResult() {
        if(result == null) registerResult();
        return getCopy(result);
    }

    public Map<Integer, ItemStack> getIngredients() {
        if(ingredients == null) registerIngredients();
        return ingredients;
    }

    public void setIngredientsSerialized(Map<Integer, String> ingredientsSerialized) {
        this.ingredientsSerialized = ingredientsSerialized;
        registerIngredients();
    }

    public void setResultSerialized(String resultSerialized) {
        this.resultSerialized = resultSerialized;
        registerResult();
    }

    private void registerResult(){
        this.result = Optional.ofNullable(resultSerialized).map(ItemStackUtils::deserialize).orElse(null);
    }

    private void registerIngredients(){
        this.ingredients = new HashMap<>();

        for(Map.Entry<Integer, String> entry : MapUtils.check(ingredientsSerialized).entrySet()){
            ingredients.put(entry.getKey(), Optional.ofNullable(entry.getValue()).map(ItemStackUtils::deserialize).orElse(null));
        }
    }

    private ItemStack getCopy(ItemStack itemStack){
        return Optional.ofNullable(itemStack).map(ItemStack::clone).orElse(null);
    }

    public void register(){
        Recipes.registerNewRecipe(this);
    }

    public boolean hasPermission(Player player){
        return recipePermission == null || player.hasPermission(recipePermission);
    }
}
