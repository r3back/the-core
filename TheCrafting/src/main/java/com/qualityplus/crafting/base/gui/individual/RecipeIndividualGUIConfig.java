package com.qualityplus.crafting.base.gui.individual;

import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.SimpleGUI;
import eu.okaeri.configs.OkaeriConfig;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public final class RecipeIndividualGUIConfig extends OkaeriConfig implements SimpleGUI{
    private final Item modifyDisplayNameItem;
    private final Item modifyPermissionItem;
    private final Item modifyCategoryItem;
    private final Item modifyRecipeItem;
    private final Item modifySlotItem;
    private final Item modifyPageItem;
    private final CommonGUI commonGUI;
    private final Item deleteItem;
    private final Item goBack;

    @Builder
    public RecipeIndividualGUIConfig(Item modifyDisplayNameItem, Item modifyPermissionItem, Item modifyCategoryItem, Item modifyRecipeItem, Item modifySlotItem, Item modifyPageItem,
                                     CommonGUI commonGUI, Item goBack, Item deleteItem) {
        this.modifyDisplayNameItem = modifyDisplayNameItem;
        this.modifyPermissionItem = modifyPermissionItem;
        this.modifyCategoryItem = modifyCategoryItem;
        this.modifyRecipeItem = modifyRecipeItem;
        this.modifySlotItem = modifySlotItem;
        this.modifyPageItem = modifyPageItem;
        this.deleteItem = deleteItem;
        this.commonGUI = commonGUI;
        this.goBack = goBack;
    }
}
