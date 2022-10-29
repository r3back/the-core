package com.qualityplus.skills.gui.sub;

import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.SimpleGUI;
import eu.okaeri.configs.OkaeriConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public final class SubGUIConfig extends OkaeriConfig implements SimpleGUI {
    private CommonGUI commonGUI;
    private Item inProgressItem;
    private Item unlockedItem;
    private Item lockedItem;

    private Item previousPage;
    private Item nextPage;

    private Item goBack;

    private List<Integer> levelSlots;

    private Item categoryItem;
}
