package com.qualityplus.crafting.base.gui.craftingtable;

import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.SimpleGUI;
import eu.okaeri.configs.OkaeriConfig;
import lombok.Builder;
import lombok.Getter;
import org.checkerframework.checker.units.qual.C;

import java.util.List;

@Getter
public final class CraftingTableGUIConfig extends OkaeriConfig implements SimpleGUI {
    private final List<Integer> autoRecipeSlots;
    private final Item customGoBackItem;
    private final List<Integer> recipeSlots;
    private final Item resultItemFilled;
    private final Item resultItemEmpty;
    private final CommonGUI commonGUI;
    private final int resultSlot;

    @Builder
    public CraftingTableGUIConfig(CommonGUI commonGUI, List<Integer> autoRecipeSlots, List<Integer> recipeSlots, int resultSlot, Item resultItemFilled, Item resultItemEmpty, Item customGoBackItem) {
        this.customGoBackItem = customGoBackItem;
        this.resultItemFilled = resultItemFilled;
        this.resultItemEmpty = resultItemEmpty;
        this.autoRecipeSlots = autoRecipeSlots;
        this.recipeSlots = recipeSlots;
        this.resultSlot = resultSlot;
        this.commonGUI = commonGUI;
    }
}
