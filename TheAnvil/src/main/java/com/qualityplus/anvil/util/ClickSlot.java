package com.qualityplus.anvil.util;

import com.qualityplus.assistant.inventory.Item;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class ClickSlot {
    public boolean isSlot(final int slot, final Item item) {
        return item != null && item.enabled && item.slot == slot;
    }
}
