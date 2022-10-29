package com.qualityplus.assistant.inventory;

import com.qualityplus.assistant.inventory.background.Background;

public interface SimpleGUI {
    CommonGUI getCommonGUI();

    default int getSize(){
        return getCommonGUI().size;
    }

    default String getTitle() {
        return getCommonGUI().title;
    }

    default Background getBackground() {
        return getCommonGUI().background;
    }

    default Item getCloseGUI() {
        return getCommonGUI().closeGUI;
    }
}
