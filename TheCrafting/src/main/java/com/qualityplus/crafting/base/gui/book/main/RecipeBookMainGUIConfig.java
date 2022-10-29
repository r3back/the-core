package com.qualityplus.crafting.base.gui.book.main;

import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.SimpleGUI;
import eu.okaeri.configs.OkaeriConfig;
import lombok.Builder;
import lombok.Getter;

@Getter
public final class RecipeBookMainGUIConfig extends OkaeriConfig implements SimpleGUI{
    private final Item generalProgressItem;
    private final CommonGUI commonGUI;
    private final Item categoryItem;

    @Builder
    public RecipeBookMainGUIConfig(Item generalProgressItem, CommonGUI commonGUI, Item categoryItem) {
        this.generalProgressItem = generalProgressItem;
        this.commonGUI = commonGUI;
        this.categoryItem = categoryItem;
    }
}
