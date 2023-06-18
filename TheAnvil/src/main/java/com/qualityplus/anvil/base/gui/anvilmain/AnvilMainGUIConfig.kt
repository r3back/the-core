package com.qualityplus.anvil.base.gui.anvilmain

import com.qualityplus.assistant.inventory.CommonGUI
import com.qualityplus.assistant.inventory.Item
import com.qualityplus.assistant.inventory.SimpleGUI
import eu.okaeri.configs.OkaeriConfig
import lombok.Builder
import lombok.Getter

data class AnvilMainGUIConfig @Builder constructor(
    val toUpgradeFilledSlots: List<Int>,
    val toUpgradeFilledItem: Item,
    val toUpgradeSlot: Int,
    val commonGui: CommonGUI,
    val toSacrificeFilledSlots: List<Int>,
    val toSacrificeFilledItem: Item,
    val toSacrificeSlot: Int,
    val readyToCombineSlots: List<Int>,
    val readyToCombineItem: Item,
    val combineFilledItem: Item,
    val combinedErrorItem: Item,
    val combinedFilledItem: Item
) : OkaeriConfig(), SimpleGUI
