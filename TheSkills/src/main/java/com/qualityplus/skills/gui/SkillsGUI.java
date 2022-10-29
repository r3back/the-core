package com.qualityplus.skills.gui;

import com.qualityplus.assistant.api.util.IPlaceholder;
import com.qualityplus.assistant.inventory.GUI;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.SimpleGUI;
import com.qualityplus.skills.api.box.Box;

import java.util.List;
import java.util.UUID;

public abstract class SkillsGUI extends GUI {
    protected final Box box;
    protected UUID uuid;

    public SkillsGUI(int size, String title, Box box) {
        super(size, title);
        this.box = box;
    }

    public SkillsGUI(SimpleGUI simpleGUI, Box box) {
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
