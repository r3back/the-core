package com.qualityplus.auction.base.gui.stats;

import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.SimpleGUI;
import eu.okaeri.configs.OkaeriConfig;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

public final class AuctionStatsGUIConfig extends OkaeriConfig implements SimpleGUI {
    public @Getter CommonGUI commonGUI;
    public Item sellerStatsItem;
    public Item buyerStatsItem;
    public Item goBackItem;

    @Builder
    public AuctionStatsGUIConfig(CommonGUI commonGUI, Item sellerStatsItem, Item buyerStatsItem, Item goBackItem) {
        this.commonGUI = commonGUI;
        this.sellerStatsItem = sellerStatsItem;
        this.buyerStatsItem = buyerStatsItem;
        this.goBackItem = goBackItem;
    }
}
