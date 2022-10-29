package com.qualityplus.auction.base.gui.pending;

import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.SimpleGUI;
import eu.okaeri.configs.OkaeriConfig;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

public final class PendingAuctionGUIConfig extends OkaeriConfig implements SimpleGUI {
    public @Getter CommonGUI commonGUI;
    public Item auctionItem;

    public Item goBackItem;
    public Item previousPageItem;
    public Item nextPageItem;
    public List<Integer> auctionSlots;

    @Builder
    public PendingAuctionGUIConfig(CommonGUI commonGUI, Item auctionItem, Item goBackItem, Item previousPageItem, Item nextPageItem, List<Integer> auctionSlots) {
        this.commonGUI = commonGUI;
        this.auctionItem = auctionItem;
        this.goBackItem = goBackItem;
        this.previousPageItem = previousPageItem;
        this.nextPageItem = nextPageItem;
        this.auctionSlots = auctionSlots;
    }
}
