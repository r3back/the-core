package com.qualityplus.dragon.gui.altars;

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
public final class DragonAltarsGUIConfig extends OkaeriConfig implements SimpleGUI {
    private CommonGUI commonGUI;
    private Item altarItem;
    private Item previousPage;
    private Item nextPage;
    private Item backToMainMenu;
}
