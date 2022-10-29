package com.qualityplus.assistant.util.armor;

import com.qualityplus.assistant.util.itemstack.ItemStackUtils;
import org.bukkit.inventory.ItemStack;

public enum ArmorType {
    HELMET(5), CHESTPLATE(6), LEGGINGS(7), BOOTS(8);

    private final int slot;

    ArmorType(int slot) {
        this.slot = slot;
    }
    public static ArmorType matchType(final ItemStack itemStack) {
        if (ItemStackUtils.isNull(itemStack)) return null;
        String type = itemStack.getType().name();
        if (type.endsWith("_HELMET") || type.endsWith("_SKULL") || type.endsWith("_HEAD")) return HELMET;
        else if (type.endsWith("_CHESTPLATE") || type.equals("ELYTRA")) return CHESTPLATE;
        else if (type.endsWith("_LEGGINGS")) return LEGGINGS;
        else if (type.endsWith("_BOOTS")) return BOOTS;
        else return null;
    }

    public int getSlot() {
        return slot;
    }
}