package com.qualityplus.auction.base.gui.create;

import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.SimpleGUI;
import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;
import lombok.Builder;
import lombok.Getter;

/**
 * utility class for create auction
 */
@Getter
public final class CreateAuctionGUIConfig extends OkaeriConfig implements SimpleGUI {
    private CommonGUI commonGUI;
    private Item currentItemEmpty;
    private Item currentItemFilled;
    private Item createAuctionEmpty;
    private Item createAuctionFilled;
    private Item auctionInitialBid;
    private Item auctionItemPrice;
    private Item auctionDuration;
    private Item goBack;
    private Item switchToAuction;
    private Item switchToBin;

    /**
     * Adds create auction gui config
     *
     * @param commonGUI            {@link CommonGUI}
     * @param auctionItemPrice     {@link CreateAuctionGUIConfig}
     * @param currentItemEmpty     {@link CreateAuctionGUIConfig}
     * @param currentItemFilled    {@link CreateAuctionGUIConfig}
     * @param createAuctionEmpty   {@link CreateAuctionGUIConfig}
     * @param createAuctionFilled  {@link CreateAuctionGUIConfig}
     * @param auctionInitialBid    {@link CreateAuctionGUIConfig}
     * @param auctionDuration      {@link CreateAuctionGUIConfig}
     * @param goBack               {@link CreateAuctionGUIConfig}
     * @param switchToAuction      {@link CreateAuctionGUIConfig}
     * @param switchToBin          {@link CreateAuctionGUIConfig}
     */
    @Builder
    public CreateAuctionGUIConfig(final CommonGUI commonGUI,
                                  final Item auctionItemPrice,
                                  final Item currentItemEmpty,
                                  final Item currentItemFilled,
                                  final Item createAuctionEmpty,
                                  final Item createAuctionFilled,
                                  final Item auctionInitialBid,
                                  final Item auctionDuration,
                                  final Item goBack,
                                  final Item switchToAuction,
                                  final Item switchToBin) {
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
