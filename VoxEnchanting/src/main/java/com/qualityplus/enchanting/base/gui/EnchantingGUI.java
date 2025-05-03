package com.qualityplus.enchanting.base.gui;

import com.qualityplus.assistant.api.util.IPlaceholder;
import com.qualityplus.assistant.inventory.GUI;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.SimpleGUI;
import com.qualityplus.enchanting.api.box.Box;

import java.util.List;
import java.util.UUID;

public abstract class EnchantingGUI extends GUI {
    protected final Box box;
    protected UUID uuid;

    public EnchantingGUI(int size, String title, Box box) {
        super(size, title);

        this.box = box;
    }

    public EnchantingGUI(SimpleGUI simpleGUI, Box box) {
        super(simpleGUI);

        this.box = box;
    }

    public void setItem(Item item) {
        setItem(item, box.files().config().loreWrapper);
    }

    public void setItem(Item item, List<IPlaceholder> placeholderList) {
        setItem(item, placeholderList, box.files().config().loreWrapper);
    }
}
