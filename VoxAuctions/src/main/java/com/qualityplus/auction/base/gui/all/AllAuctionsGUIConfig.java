package com.qualityplus.auction.base.gui.all;

import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.SimpleGUI;
import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * Utility class for all auctions gui config
  */
@Getter
public final class AllAuctionsGUIConfig extends OkaeriConfig implements SimpleGUI {
    private CommonGUI commonGUI;
    private Item categoryItem;

    private Item byNameFilterEmptyItem;
    private Item byNameFilterFilledItem;

    private Item binFilterItem;
    private Item sortItem;

    private Item resetSettings;
    private Item auctionItem;
    private Item previousPage;
    private Item nextPage;
    private Item goBack;

    private List<Integer> auctionSlots;

    /**
     * Adds all auction gui
     *
     * @param commonGUI {@link CommonGUI}
     * @param categoryItem {@link AllAuctionsGUIConfig}
     * @param byNameFilterEmptyItem {@link AllAuctionsGUIConfig}
     * @param byNameFilterFilledItem {@link AllAuctionsGUIConfig}
     * @param binFilterItem {@link AllAuctionsGUIConfig}
     * @param sortItem {@link AllAuctionsGUIConfig}
     * @param auctionItem {@link AllAuctionsGUIConfig}
     * @param previousPage {@link AllAuctionsGUIConfig}
     * @param nextPage {@link AllAuctionsGUIConfig}
     * @param goBack {@link AllAuctionsGUIConfig}
     * @param auctionSlots {@link AllAuctionsGUIConfig}
     * @param resetSettings {@link AllAuctionsGUIConfig}
     */
    @Builder
    public AllAuctionsGUIConfig(final CommonGUI commonGUI,
                                final Item categoryItem,
                                final Item byNameFilterEmptyItem,
                                final Item byNameFilterFilledItem,
                                final Item binFilterItem,
                                final Item sortItem,
                                final Item auctionItem,
                                final Item previousPage,
                                final Item nextPage,
                                final Item goBack,
                                final List<Integer> auctionSlots,
                                final Item resetSettings) {
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
