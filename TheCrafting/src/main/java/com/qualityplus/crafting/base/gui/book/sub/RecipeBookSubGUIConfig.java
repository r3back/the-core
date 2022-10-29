package com.qualityplus.crafting.base.gui.book.sub;

import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.SimpleGUI;
import eu.okaeri.configs.OkaeriConfig;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public final class RecipeBookSubGUIConfig extends OkaeriConfig implements SimpleGUI{
    private final CommonGUI commonGUI;
    private final Item categoryItem;
    private final Item unlockedItem;
    private final Item lockedItem;
    private final Item goBack;

    @Builder
    public RecipeBookSubGUIConfig(CommonGUI commonGUI, Item categoryItem, Item unlockedItem, Item lockedItem, Item goBack) {
        this.commonGUI = commonGUI;
        this.categoryItem = categoryItem;
        this.unlockedItem = unlockedItem;
        this.lockedItem = lockedItem;
        this.goBack = goBack;
    }
}
