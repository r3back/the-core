package com.qualityplus.enchanting.base.gui.enchantmain;

import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.SimpleGUI;
import eu.okaeri.configs.OkaeriConfig;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public final class EnchantMainGUIConfig extends OkaeriConfig implements SimpleGUI{
    private final Item enchantItemNoBookShelfPower;
    private final List<Integer> enchantmentSlots;
    private final Item cannotEnchantItem;
    private final CommonGUI commonGUI;
    private final Item bookShelfItem;
    private final Item nextPageItem;
    private final Item backPageItem;
    private final Item enchantItem;
    private final Item emptyItem;

    @Builder
    public EnchantMainGUIConfig(Item bookShelfItem, Item nextPageItem, Item backPageItem, CommonGUI commonGUI, Item enchantItem, Item enchantItemNoBookShelfPower, List<Integer> enchantmentSlots,
                                Item emptyItem, Item cannotEnchantItem) {
        this.enchantItemNoBookShelfPower = enchantItemNoBookShelfPower;
        this.cannotEnchantItem = cannotEnchantItem;
        this.enchantmentSlots = enchantmentSlots;
        this.bookShelfItem = bookShelfItem;
        this.nextPageItem = nextPageItem;
        this.backPageItem = backPageItem;
        this.enchantItem = enchantItem;
        this.emptyItem = emptyItem;
        this.commonGUI = commonGUI;
    }
}
