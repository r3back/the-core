package com.qualityplus.auction.base.gui.all;

import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.SimpleGUI;
import eu.okaeri.configs.OkaeriConfig;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public final class AllAuctionsGUIConfig extends OkaeriConfig implements SimpleGUI {
    public @Getter CommonGUI commonGUI;
    public Item categoryItem;

    public Item byNameFilterEmptyItem;
    public Item byNameFilterFilledItem;

    public Item binFilterItem;
    public Item sortItem;

    public Item resetSettings;
    public Item auctionItem;
    public Item previousPage;
    public Item nextPage;
    public Item goBack;

    public List<Integer> auctionSlots;

    @Builder
    public AllAuctionsGUIConfig(CommonGUI commonGUI, Item categoryItem, Item byNameFilterEmptyItem, Item byNameFilterFilledItem, Item binFilterItem, Item sortItem, Item auctionItem, Item previousPage, Item nextPage, Item goBack,
                                List<Integer> auctionSlots, Item resetSettings) {
        this.byNameFilterEmptyItem = byNameFilterEmptyItem;
        this.byNameFilterFilledItem = byNameFilterFilledItem;
        this.binFilterItem = binFilterItem;
        this.resetSettings = resetSettings;
        this.categoryItem = categoryItem;
        this.previousPage = previousPage;
        this.auctionSlots = auctionSlots;
        this.auctionItem = auctionItem;
        this.commonGUI = commonGUI;
        this.sortItem = sortItem;
        this.nextPage = nextPage;
        this.goBack = goBack;

    }
}
