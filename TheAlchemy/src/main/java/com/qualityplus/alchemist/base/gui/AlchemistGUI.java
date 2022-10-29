package com.qualityplus.alchemist.base.gui;

import com.qualityplus.alchemist.api.box.Box;
import com.qualityplus.assistant.api.util.IPlaceholder;
import com.qualityplus.assistant.inventory.GUI;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.SimpleGUI;

import java.util.List;

public abstract class AlchemistGUI extends GUI {
    protected final Box box;

    public AlchemistGUI(int size, String title, Box box) {
        super(size, title);

        this.box = box;
    }

    public AlchemistGUI(SimpleGUI simpleGUI, Box box) {
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
