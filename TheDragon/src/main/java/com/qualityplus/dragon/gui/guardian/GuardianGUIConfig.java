package com.qualityplus.dragon.gui.guardian;

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
public final class GuardianGUIConfig extends OkaeriConfig implements SimpleGUI {
    private CommonGUI commonGUI;
    private Item goBackItem;
    private Item helmetItem;
    private Item chestPlateItem;
    private Item leggingsItem;
    private Item bootsItem;
    private Item mobTypeItem;
    private Item displayNameItem;
    private Item healthItem;
    private Item weaponItem;

}
