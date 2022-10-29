package com.qualityplus.auction.util;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.assistant.api.gui.LoreWrapper;
import com.qualityplus.assistant.api.util.IPlaceholder;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.assistant.util.itemstack.ItemBuilder;
import com.qualityplus.assistant.util.itemstack.ItemStackUtils;
import com.qualityplus.auction.api.gui.GUIDisplayItem;
import lombok.experimental.UtilityClass;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;
import java.util.List;

@UtilityClass
public class AuctionItemStackUtils {
    public static ItemStack makeCategoryItem(Item item, List<IPlaceholder> placeholders, GUIDisplayItem guiDisplayItem, LoreWrapper wrapper){
        try {
            Item item1 = ItemBuilder.of(guiDisplayItem.getIcon(), 1, 1, "", Collections.emptyList()).headData(guiDisplayItem.getTexture())
                    .enchanted(item.enchanted)
                    .build();

            ItemStack firstProcess = ItemStackUtils.makeItem(
                    guiDisplayItem.getIcon(),
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
