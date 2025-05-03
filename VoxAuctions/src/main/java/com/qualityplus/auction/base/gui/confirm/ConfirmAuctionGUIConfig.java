package com.qualityplus.auction.base.gui.confirm;

import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.SimpleGUI;
import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;
import lombok.Builder;
import lombok.Getter;

/**
 * Utility class for confirm auction
 */
@Getter
public final class ConfirmAuctionGUIConfig extends OkaeriConfig implements SimpleGUI {
    private CommonGUI commonGUI;
    private Item auctionItem;
    private Item confirmItem;
    private Item cancelItem;

    /**
     * make confirm auction
     *
     * @param commonGUI   {@link CommonGUI}
     * @param auctionItem {@link ConfirmAuctionGUIConfig}
     * @param confirmItem {@link ConfirmAuctionGUIConfig}
     * @param cancelItem  {@link ConfirmAuctionGUIConfig}
     */
    @Builder
    public ConfirmAuctionGUIConfig(final CommonGUI commonGUI, final Item auctionItem, final Item confirmItem, final Item cancelItem) {
        this.commonGUI = commonGUI;
        this.auctionItem = auctionItem;
        this.confirmItem = confirmItem;
        this.cancelItem = cancelItem;
    }
}
