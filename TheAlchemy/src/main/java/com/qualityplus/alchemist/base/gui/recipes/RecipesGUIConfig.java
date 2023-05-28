package com.qualityplus.alchemist.base.gui.recipes;

import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.SimpleGUI;
import eu.okaeri.configs.OkaeriConfig;
import lombok.Builder;
import lombok.Getter;

/**
 * Recipes GUI Configuration
 */
@Getter
public final class RecipesGUIConfig extends OkaeriConfig implements SimpleGUI {
    private final CommonGUI commonGUI;
    private final Item recipeItem;
    private final Item previousPage;
    private final Item nextPage;

    /**
     *
     * @param commonGUI    {@link CommonGUI}
     * @param recipeItem   {@link Item}
     * @param previousPage {@link Item}
     * @param nextPage     {@link Item}
     */
    @Builder
    public RecipesGUIConfig(final CommonGUI commonGUI, final Item recipeItem, final Item previousPage, final Item nextPage) {
        this.commonGUI = commonGUI;
        this.recipeItem = recipeItem;
        this.previousPage = previousPage;
        this.nextPage = nextPage;
    }
}
