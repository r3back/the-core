package com.qualityplus.skills.util;

import com.qualityplus.assistant.lib.com.cryptomorin.xseries.XMaterial;
import com.qualityplus.assistant.api.util.BukkitItemUtil;
import com.qualityplus.assistant.api.util.IPlaceholder;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.assistant.util.itemstack.ItemBuilder;
import com.qualityplus.assistant.util.itemstack.ItemStackUtils;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import lombok.experimental.UtilityClass;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;
import java.util.List;

/**
 * Utility class for skills item stack
 */
@UtilityClass
public final class SkillsItemStackUtil {
    /**
     * Makes an item stack
     *
     * @param item               {@link Item}
     * @param placeholders       list of {@link IPlaceholder}
     * @param skillGUIOptions    {@link GUIOptions}
     * @return                   {@link ItemStack}
     */
    public static ItemStack makeItem(final Item item, final List<IPlaceholder> placeholders, final GUIOptions skillGUIOptions) {
        try {
            final Item item1 = ItemBuilder.of(skillGUIOptions.getItem(), 1, 1, "", Collections.emptyList()).headData(skillGUIOptions.getTexture()).build();

            final ItemStack firstProcess = ItemStackUtils.makeItem(
                    skillGUIOptions.getItem(),
                    1,
                    StringUtils.processMulti(item.displayName, placeholders),
                    StringUtils.processMulti(item.lore, placeholders));

            BukkitItemUtil.addCustomModelData(firstProcess, skillGUIOptions.getCustomModelData());


            return ItemStackUtils.getFinalItem(item1, firstProcess, placeholders);
        } catch (Exception e) {
            e.printStackTrace();
            return XMaterial.STONE.parseItem();
        }
    }


}
