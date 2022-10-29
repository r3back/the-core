package com.qualityplus.dragon.gui;

import com.qualityplus.assistant.api.util.IPlaceholder;
import com.qualityplus.assistant.inventory.GUI;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.SimpleGUI;
import com.qualityplus.dragon.api.box.Box;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.InventoryHolder;

import java.util.List;
import java.util.UUID;

public abstract class TheDragonGUI extends GUI {
    protected final Box box;
    protected UUID uuid;

    public TheDragonGUI(int size, String title, Box box) {
        super(size, title);
        this.box = box;
    }

    public TheDragonGUI(SimpleGUI simpleGUI, Box box) {
        super(simpleGUI);
        this.box = box;
    }

    public void setItem(Item item) {
        super.setItem(item);
    }

    public void setItem(Item item, List<IPlaceholder> placeholderList) {
        super.setItem(item, placeholderList);
    }
}
