package com.qualityplus.crafting.base.gui.recipes;

import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.SimpleGUI;
import eu.okaeri.configs.OkaeriConfig;
import lombok.Builder;
import lombok.Getter;

@Getter
public final class RecipesGUIConfig extends OkaeriConfig implements SimpleGUI{
    private final CommonGUI commonGUI;
    private final Item recipeItem;
    private final Item previousPage;
    private final Item nextPage;

    @Builder
    public RecipesGUIConfig(CommonGUI commonGUI, Item recipeItem, Item previousPage, Item nextPage) {
        this.commonGUI = commonGUI;
        this.recipeItem = recipeItem;
        this.previousPage = previousPage;
        this.nextPage = nextPage;
    }
}
