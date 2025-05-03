package com.qualityplus.crafting.base.gui.book.sub;

import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.SimpleGUI;
import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class RecipeBookSubGUIConfig extends OkaeriConfig implements SimpleGUI{
    private CommonGUI commonGUI;
    private Item categoryItem;
    private Item unlockedItem;
    private Item lockedItem;
    private Item goBack;
    private Item previousPage;
    private Item nextPage;

    @Builder
    public RecipeBookSubGUIConfig(CommonGUI commonGUI, Item categoryItem, Item unlockedItem, Item lockedItem, Item goBack,
                                  Item previousPage, Item nextPage) {
        this.commonGUI = commonGUI;
        this.categoryItem = categoryItem;
        this.unlockedItem = unlockedItem;
        this.lockedItem = lockedItem;
        this.goBack = goBack;
        this.previousPage = previousPage;
        this.nextPage = nextPage;
    }
}
