package com.qualityplus.minions.base.gui.main;

import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.SimpleGUI;
import eu.okaeri.configs.OkaeriConfig;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public final class MainMinionGUIConfig extends OkaeriConfig implements SimpleGUI{
    private final CommonGUI commonGUI;
    private final Item minionSkinEmptyItem;
    private final Item minionFuelItem;
    private final Item minionAutomatedShipping;
    private final Item firstUpgradeEmptyItem;
    private final Item secondUpgradeEmptyItem;
    private final Item idealLayoutItem;
    private final Item minionItem;
    private final Item minionTierItem;
    private final Item collectAllItem;
    private final Item quickUpgradeItem;
    private final Item pickUpMinion;
    private final Item storageSlotLocked;
    private final List<Integer> storageSlots;
    private final Item automatedShippingPlacedItem;
    private final Item fuelPlacedItem;
    private final Item firstUpgradePlacedItem;
    private final Item secondUpgradePlacedItem;
    private final Item minionSkinPlacedItem;

    @Builder
    public MainMinionGUIConfig(CommonGUI commonGUI, Item minionSkinEmptyItem, Item minionFuelItem,
            Item minionAutomatedShipping, Item firstUpgradeEmptyItem, Item secondUpgradeEmptyItem, Item idealLayoutItem,
            Item minionItem, Item minionTierItem, Item collectAllItem, Item quickUpgradeItem, Item pickUpMinion,Item storageSlotLocked,
            List<Integer> storageSlots, Item automatedShippingPlacedItem, Item fuelPlacedItem, Item firstUpgradePlacedItem, Item secondUpgradePlacedItem,
                               Item minionSkinPlacedItem) {
        this.minionAutomatedShipping = minionAutomatedShipping;
        this.secondUpgradeEmptyItem = secondUpgradeEmptyItem;
        this.firstUpgradeEmptyItem = firstUpgradeEmptyItem;
        this.storageSlotLocked = storageSlotLocked;
        this.quickUpgradeItem = quickUpgradeItem;
        this.idealLayoutItem = idealLayoutItem;
        this.minionSkinEmptyItem = minionSkinEmptyItem;
        this.minionFuelItem = minionFuelItem;
        this.minionTierItem = minionTierItem;
        this.collectAllItem = collectAllItem;
        this.pickUpMinion = pickUpMinion;
        this.storageSlots = storageSlots;
        this.minionItem = minionItem;
        this.commonGUI = commonGUI;
        this.fuelPlacedItem = fuelPlacedItem;
        this.firstUpgradePlacedItem = firstUpgradePlacedItem;
        this.secondUpgradePlacedItem = secondUpgradePlacedItem;
        this.automatedShippingPlacedItem = automatedShippingPlacedItem;
        this.minionSkinPlacedItem = minionSkinPlacedItem;
    }
}
