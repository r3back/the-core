package com.qualityplus.collections.util;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.assistant.api.util.IPlaceholder;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.assistant.util.itemstack.ItemBuilder;
import com.qualityplus.assistant.util.itemstack.ItemStackUtils;
import com.qualityplus.collections.TheCollections;
import com.qualityplus.collections.base.collection.gui.GUIOptions;
import lombok.experimental.UtilityClass;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.Metadatable;

import java.util.Collections;
import java.util.List;

@UtilityClass
public final class CollectionsItemStackUtil {
    public static ItemStack makeItem(Item item, List<IPlaceholder> placeholders, GUIOptions guiOptions){
        try {
            Item item1 = ItemBuilder.of(guiOptions.getItem(), 1, 1, "", Collections.emptyList()).headData(guiOptions.getTexture()).build();

            ItemStack firstProcess = ItemStackUtils.makeItem(
                    guiOptions.getItem(),
                    1,
                    StringUtils.processMulti(item.displayName, placeholders),
                    StringUtils.processMulti(item.lore, placeholders));

            ItemStackUtils.addCustomModelData(firstProcess, guiOptions.getCustomModelData());

            return ItemStackUtils.getFinalItem(item1, firstProcess, placeholders);
        } catch (Exception e) {
            e.printStackTrace();
            return XMaterial.STONE.parseItem();
        }
    }
}
