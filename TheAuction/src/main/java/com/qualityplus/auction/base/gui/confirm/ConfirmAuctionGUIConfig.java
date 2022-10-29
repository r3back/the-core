package com.qualityplus.auction.base.gui.confirm;

import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.SimpleGUI;
import eu.okaeri.configs.OkaeriConfig;
import lombok.Builder;
import lombok.Getter;

public final class ConfirmAuctionGUIConfig extends OkaeriConfig implements SimpleGUI {
    public @Getter CommonGUI commonGUI;
    public Item auctionItem;
    public Item confirmItem;
    public Item cancelItem;

    @Builder
    public ConfirmAuctionGUIConfig(CommonGUI commonGUI, Item auctionItem, Item confirmItem, Item cancelItem) {
        this.commonGUI = commonGUI;
        this.auctionItem = auctionItem;
        this.confirmItem = confirmItem;
        this.cancelItem = cancelItem;
    }
}
