package com.qualityplus.crafting.base.gui.modify;

import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.SimpleGUI;
import eu.okaeri.configs.OkaeriConfig;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public final class ModifyRecipeGUIConfig extends OkaeriConfig implements SimpleGUI{
    private final List<Integer> recipeSlots;
    private final CommonGUI commonGUI;
    private final Item saveRecipe;
    private final int resultSlot;
    private final Item goBack;

    @Builder
    public ModifyRecipeGUIConfig(List<Integer> recipeSlots, CommonGUI commonGUI, Item saveRecipe, int resultSlot, Item goBack) {
        this.recipeSlots = recipeSlots;
        this.commonGUI = commonGUI;
        this.saveRecipe = saveRecipe;
        this.resultSlot = resultSlot;
        this.goBack = goBack;
    }
}
