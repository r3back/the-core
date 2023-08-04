package com.qualityplus.auction.util;

import com.qualityplus.assistant.lib.com.cryptomorin.xseries.XMaterial;
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

/**
 * Utility class for auction item stacks
 */
@UtilityClass
public class AuctionItemStackUtils {
    /**
     * Makes a category for an item
     *
     * @param item           {@link Item}
     * @param placeholders   List of {@link IPlaceholder}
     * @param guiDisplayItem {@link GUIDisplayItem}
     * @param wrapper        {@link LoreWrapper}
     * @return an {@link ItemStack}
     */

    public static ItemStack makeCategoryItem(final Item item,
                                             final List<IPlaceholder> placeholders,
                                             final GUIDisplayItem guiDisplayItem,
                                             final LoreWrapper wrapper) {
        try {
            final Item item1 = ItemBuilder.of(guiDisplayItem.getIcon(), 1, 1, "", Collections.emptyList()).headData(guiDisplayItem.getTexture())
                    .enchanted(item.enchanted)
                    .build();

            final ItemStack firstProcess = ItemStackUtils.makeItem(
                    guiDisplayItem.getIcon(),
                    1,
                    StringUtils.processMulti(item.displayName, placeholders),
                    StringUtils.processMulti(item.lore, placeholders));

            return ItemStackUtils.getFinalItem(item1, firstProcess, placeholders, wrapper);
        } catch (NullPointerException e) {
            e.printStackTrace();
            return XMaterial.STONE.parseItem();
        }
    }
}
