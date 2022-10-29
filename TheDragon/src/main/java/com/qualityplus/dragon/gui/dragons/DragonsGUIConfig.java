package com.qualityplus.dragon.gui.dragons;

import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.SimpleGUI;
import eu.okaeri.configs.OkaeriConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public final class DragonsGUIConfig extends OkaeriConfig implements SimpleGUI {
    private CommonGUI commonGUI;
    private Item dragonItem;
    private Item previousPage;
    private Item nextPage;
    private Item backToMainMenu;
}
