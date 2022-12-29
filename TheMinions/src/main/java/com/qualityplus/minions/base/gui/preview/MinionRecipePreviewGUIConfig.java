package com.qualityplus.minions.base.gui.preview;

import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.SimpleGUI;
import eu.okaeri.configs.OkaeriConfig;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public final class MinionRecipePreviewGUIConfig extends OkaeriConfig implements SimpleGUI{
    private final List<Integer> recipeSlots;
    private final CommonGUI commonGUI;
    private final int resultSlot;
    private final Item goBack;

    @Builder
    public MinionRecipePreviewGUIConfig(List<Integer> recipeSlots, CommonGUI commonGUI, int resultSlot, Item goBack) {
        this.recipeSlots = recipeSlots;
        this.commonGUI = commonGUI;
        this.resultSlot = resultSlot;
        this.goBack = goBack;
    }
}
