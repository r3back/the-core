package com.qualityplus.minions.base.gui.recipes;

import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.SimpleGUI;
import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public final class MinionRecipesGUIConfig extends OkaeriConfig implements SimpleGUI{
    private final Map<Integer, Integer> levelSlotsMap;
    private final CommonGUI commonGUI;
    private final Item minionItem;
    private final Item goBack;

    @Builder
    public MinionRecipesGUIConfig(Map<Integer, Integer> levelSlotsMap, CommonGUI commonGUI, Item minionItem, Item goBack) {
        this.levelSlotsMap = levelSlotsMap;
        this.commonGUI = commonGUI;
        this.minionItem = minionItem;
        this.goBack = goBack;
    }
}
