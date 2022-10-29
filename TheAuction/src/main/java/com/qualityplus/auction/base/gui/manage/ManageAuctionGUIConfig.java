package com.qualityplus.auction.base.gui.manage;

import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.SimpleGUI;
import eu.okaeri.configs.OkaeriConfig;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

public final class ManageAuctionGUIConfig extends OkaeriConfig implements SimpleGUI {
    public @Getter CommonGUI commonGUI;
    public Item auctionItem;
    public Item auctionItemSold;

    public Item goBackItem;
    public Item sortItem;
    public Item createAnAuction;
    public Item previousPageItem;
    public Item nextPageItem;
    public List<Integer> auctionSlots;

    @Builder
    public ManageAuctionGUIConfig(CommonGUI commonGUI, Item auctionItemSold, Item auctionItem, Item goBackItem, Item sortItem, Item createAnAuction, Item previousPageItem, Item nextPageItem, List<Integer> auctionSlots) {
        this.commonGUI = commonGUI;
        this.auctionItem = auctionItem;
        this.goBackItem = goBackItem;
        this.sortItem = sortItem;
        this.createAnAuction = createAnAuction;
        this.previousPageItem = previousPageItem;
        this.nextPageItem = nextPageItem;
        this.auctionSlots = auctionSlots;
        this.auctionItemSold = auctionItemSold;
    }
}
