package com.qualityplus.auction.base.gui.create;

import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.SimpleGUI;
import eu.okaeri.configs.OkaeriConfig;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public final class CreateAuctionGUIConfig extends OkaeriConfig implements SimpleGUI {
    public @Getter CommonGUI commonGUI;
    public Item currentItemEmpty;
    public Item currentItemFilled;
    public Item createAuctionEmpty;
    public Item createAuctionFilled;
    public Item auctionInitialBid;
    public Item auctionItemPrice;
    public Item auctionDuration;
    public Item goBack;
    public Item switchToAuction;
    public Item switchToBin;

    @Builder
    public CreateAuctionGUIConfig(CommonGUI commonGUI, Item auctionItemPrice, Item currentItemEmpty, Item currentItemFilled, Item createAuctionEmpty, Item createAuctionFilled, Item auctionInitialBid,
                                  Item auctionDuration, Item goBack, Item switchToAuction, Item switchToBin) {
        this.commonGUI = commonGUI;
        this.currentItemEmpty = currentItemEmpty;
        this.currentItemFilled = currentItemFilled;
        this.createAuctionEmpty = createAuctionEmpty;
        this.createAuctionFilled = createAuctionFilled;
        this.auctionInitialBid = auctionInitialBid;
        this.auctionDuration = auctionDuration;
        this.auctionItemPrice = auctionItemPrice;
        this.goBack = goBack;
        this.switchToAuction = switchToAuction;
        this.switchToBin = switchToBin;
    }
}
