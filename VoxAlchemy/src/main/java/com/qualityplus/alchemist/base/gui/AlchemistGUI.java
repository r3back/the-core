package com.qualityplus.alchemist.base.gui;

import com.qualityplus.alchemist.api.box.Box;
import com.qualityplus.assistant.api.util.IPlaceholder;
import com.qualityplus.assistant.inventory.GUI;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.SimpleGUI;

import java.util.List;

/**
 * Abstract Alchemist GUI
 */
public abstract class AlchemistGUI extends GUI {
    protected final Box box;

    /**
     *
     * @param size  Inventory's size
     * @param title Inventory's title
     * @param box   {@link Box}
     */
    public AlchemistGUI(final int size, final String title, final Box box) {
        super(size, title);

        this.box = box;
    }

    /**
     *
     * @param simpleGUI {@link SimpleGUI}
     * @param box       {@link Box}
     */
    public AlchemistGUI(final SimpleGUI simpleGUI, final Box box) {
        super(simpleGUI);

        this.box = box;
    }

    /**
     * Set item in inventory
     *
     * @param item {@link Item}
     */
    public void setItem(final Item item) {
        this.setItem(item, this.box.getFiles().config().getLoreWrapper());
    }

    /**
     * Set item with parsed placeholders in inventory
     *
     * @param item            {@link Item}
     * @param placeholderList List of {@link IPlaceholder}
     */
    public void setItem(final Item item, final List<IPlaceholder> placeholderList) {
        this.setItem(item, placeholderList, this.box.getFiles().config().getLoreWrapper());
    }
}
