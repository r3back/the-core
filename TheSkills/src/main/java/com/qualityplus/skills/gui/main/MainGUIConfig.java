package com.qualityplus.skills.gui.main;

import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.SimpleGUI;
import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Utility class for main gui config
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public final class MainGUIConfig extends OkaeriConfig implements SimpleGUI {
    private CommonGUI commonGUI;
    private Item skillsItem;
    private Item playerInfoItem;
    private Item customGoBackItem;
}
