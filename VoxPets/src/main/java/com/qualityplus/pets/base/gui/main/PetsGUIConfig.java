package com.qualityplus.pets.base.gui.main;

import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.SimpleGUI;
import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public final class PetsGUIConfig extends OkaeriConfig implements SimpleGUI {
    private CommonGUI commonGUI;
    private Item petItem;
    private Item petMenuInfoItem;
    private Item convertPetToItemEnabled;
    private Item convertPetToItemDisabled;
    private Item hidePetsEnabled;
    private Item hidePetsDisabled;
    private List<Integer> slots;
    private Item customGoBackItem;
}
