package com.qualityplus.anvil.base.gui;

import com.qualityplus.anvil.api.box.Box;
import com.qualityplus.assistant.api.util.IPlaceholder;
import com.qualityplus.assistant.inventory.GUI;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.SimpleGUI;
import java.util.List;
import java.util.UUID;

public abstract class AnvilGUI extends GUI {
    protected final Box box;
    protected UUID uuid;

    public AnvilGUI(int size, String title, Box box) {
        super(size, title);

        this.box = box;
    }

    public AnvilGUI(SimpleGUI simpleGUI, Box box) {
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
