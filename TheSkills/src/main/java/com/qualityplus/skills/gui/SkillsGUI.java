package com.qualityplus.skills.gui;

import com.qualityplus.assistant.api.util.IPlaceholder;
import com.qualityplus.assistant.inventory.GUI;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.SimpleGUI;
import com.qualityplus.skills.api.box.Box;

import java.util.List;
import java.util.UUID;

/**
 * Utility class for Skills gui
 */
public abstract class SkillsGUI extends GUI {
    protected final Box box;
    protected UUID uuid;

    /**
     * Adds an skills gui
     *
     * @param size  Size
     * @param title Title
     * @param box   {@link Box}
     */
    public SkillsGUI(final int size, final String title, final Box box) {
        super(size, title);
        this.box = box;
    }

    /**
     * Adds a skills gui
     *
     * @param simpleGUI {@link SimpleGUI}
     * @param box       {@link Box}
     */
    public SkillsGUI(final SimpleGUI simpleGUI, final Box box) {
        super(simpleGUI);
        this.box = box;
    }

    /**
     * Adds an item
     *
     * @param item {@link Item}
     */
    public void setItem(final Item item) {
        super.setItem(item);
    }

    /**
     * Adds an item
     *
     * @param item            {@link Item}
     * @param placeholderList List of {@link IPlaceholder}
     */
    public void setItem(final Item item, final List<IPlaceholder> placeholderList) {
        super.setItem(item, placeholderList);
    }
}
