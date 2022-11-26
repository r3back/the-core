package com.qualityplus.dragon.gui.guardianspawns;

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
public final class GuardianSpawnsGUIConfig extends OkaeriConfig implements SimpleGUI {
    private CommonGUI commonGUI;
    private Item guardianSpawnItem;
    private Item previousPage;
    private Item nextPage;
    private Item backToMainMenu;
}
