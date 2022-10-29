package com.qualityplus.dragon.util;

import com.qualityplus.assistant.util.itemstack.ItemStackUtils;
import de.tr7zw.changeme.nbtapi.NBTItem;
import lombok.experimental.UtilityClass;
import org.bukkit.inventory.ItemStack;

@UtilityClass
public final class DragonItemStackUtil {
    private static final String ENDER_KEY_TAG = "THEDRAGON_KEY";

    public static boolean isEnderKey(ItemStack itemStack){
        if(ItemStackUtils.isNull(itemStack)) return false;

        return new NBTItem(itemStack).hasKey(ENDER_KEY_TAG);
    }

    public static ItemStack createEnderKey(ItemStack itemStack){
        if(ItemStackUtils.isNull(itemStack)) return null;

        NBTItem nbtItem = new NBTItem(itemStack);

        nbtItem.setBoolean(ENDER_KEY_TAG, true);

        return nbtItem.getItem();
    }
}