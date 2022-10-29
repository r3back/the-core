package com.qualityplus.bank.util;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.assistant.api.gui.LoreWrapper;
import com.qualityplus.assistant.api.util.IPlaceholder;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.assistant.util.itemstack.ItemBuilder;
import com.qualityplus.assistant.util.itemstack.ItemStackUtils;
import com.qualityplus.bank.api.gui.GUIDisplayItem;
import lombok.experimental.UtilityClass;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;
import java.util.List;

@UtilityClass
public class BankItemStackUtils {
    public static ItemStack makeUpgradeItem(Item item, List<IPlaceholder> placeholders, GUIDisplayItem guiDisplayItem, LoreWrapper wrapper){
        try {
            Item item1 = ItemBuilder.of(guiDisplayItem.getItem(), 1, 1, "", Collections.emptyList()).headData(guiDisplayItem.getTexture())
                    .enchanted(item.enchanted)
                    .build();

            ItemStack firstProcess = ItemStackUtils.makeItem(
                    guiDisplayItem.getItem(),
                    1,
                    StringUtils.processMulti(item.displayName, placeholders),
                    StringUtils.processMulti(item.lore, placeholders));

            return ItemStackUtils.getFinalItem(item1, firstProcess, placeholders, wrapper);
        } catch (Exception e) {
            e.printStackTrace();
            return XMaterial.STONE.parseItem();
        }
    }
}
