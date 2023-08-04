package com.qualityplus.auction.base.gui.time;

import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.SimpleGUI;
import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public final class AuctionTimeConfigGUI extends OkaeriConfig implements SimpleGUI {
    public @Getter CommonGUI commonGUI;
    public Item timeItem;
    public Item goBack;
    public List<Integer> timeSlots;

    @Builder
    public AuctionTimeConfigGUI(CommonGUI commonGUI, Item timeItem, Item goBack, List<Integer> timeSlots) {
        this.commonGUI = commonGUI;
        this.timeItem = timeItem;
        this.goBack = goBack;
        this.timeSlots = timeSlots;
    }
}
