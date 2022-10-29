package com.qualityplus.enchanting.base.gui.enchantsub;

import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.SimpleGUI;
import eu.okaeri.configs.OkaeriConfig;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public final class EnchantSubGUIConfig extends OkaeriConfig implements SimpleGUI{
    private final Map<Integer, List<Integer>> enchantmentSlotsMap;
    private final CommonGUI commonGUI;
    private final Item enchantItem;
    private final Item goBackItem;


    @Builder
    public EnchantSubGUIConfig(Map<Integer, List<Integer>> enchantmentSlotsMap, CommonGUI commonGUI, Item enchantItem, Item goBackItem) {
        this.commonGUI = commonGUI;
        this.enchantItem = enchantItem;
        this.goBackItem = goBackItem;
        this.enchantmentSlotsMap = enchantmentSlotsMap;
    }
}
