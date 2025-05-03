package com.qualityplus.dragon.gui.mainmenu;

import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.SimpleGUI;
import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public final class MainMenuGUIConfig extends OkaeriConfig implements SimpleGUI {
    private CommonGUI commonGUI;
    private Item spawnItem;
    private Item crystalItem;
    private Item altarItem;
    private Item schematicItem;
    private Item dragonsItem;
    private Item guardiansItem;
    private Item guardianSpawnsItem;
    private Item wikiTutorialItem;

}
