package com.qualityplus.dragon.gui.equipment;

import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.SimpleGUI;
import eu.okaeri.configs.OkaeriConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public final class GuardianEquipmentGUIConfig extends OkaeriConfig implements SimpleGUI {
    private CommonGUI commonGUI;
}
