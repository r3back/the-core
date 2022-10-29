package com.qualityplus.crafting.base.gui.preview;

import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.SimpleGUI;
import eu.okaeri.configs.OkaeriConfig;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public final class RecipePreviewGUIConfig extends OkaeriConfig implements SimpleGUI{
    private final List<Integer> recipeSlots;
    private final CommonGUI commonGUI;
    private final Item goBackBook;
    private final int resultSlot;

    @Builder
    public RecipePreviewGUIConfig(List<Integer> recipeSlots, CommonGUI commonGUI, Item goBackBook, int resultSlot) {
        this.recipeSlots = recipeSlots;
        this.commonGUI = commonGUI;
        this.resultSlot = resultSlot;
        this.goBackBook = goBackBook;
    }
}
