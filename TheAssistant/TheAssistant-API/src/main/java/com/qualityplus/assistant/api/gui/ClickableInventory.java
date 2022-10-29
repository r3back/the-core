package com.qualityplus.assistant.api.gui;

import com.qualityplus.assistant.api.util.IPlaceholder;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.List;

public interface ClickableInventory<T, B> {
    void onInventoryClick(InventoryClickEvent event);
    void setItem(T item, LoreWrapper loreWrapper);
    void setItem(T item, List<IPlaceholder> placeholders, LoreWrapper loreWrapper);
    void setItem(T item);
    void setItem(T item, List<IPlaceholder> placeholders);
    boolean isClickingDecoration(Integer clickedSlot, B background);
}
