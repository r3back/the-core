package com.qualityplus.auction.base.gui.view;

import com.qualityplus.auction.api.box.Box;
import com.qualityplus.auction.base.gui.AuctionGUI;
import com.qualityplus.auction.base.gui.view.bin.BinAuctionViewGUI;
import com.qualityplus.auction.base.gui.view.normal.NormalAuctionViewGUI;
import com.qualityplus.auction.base.searcher.AuctionSearcher;
import com.qualityplus.auction.persistence.data.AuctionItem;
import lombok.experimental.UtilityClass;
import org.bukkit.entity.Player;

/**
 * Utility class for view opener
 */
@UtilityClass
public class ViewOpener {
    /**
     * Adds open
     *
     * @param player      {@link Player}
     * @param auctionItem {@link AuctionItem}
     * @param box         {@link Box}
     * @param searcher    {@link AuctionSearcher}
     * @param newCost     If New Cost
     */
    public void open(final Player player, final AuctionItem auctionItem, final Box box, final AuctionSearcher searcher, final double newCost) {
        final AuctionGUI gui = auctionItem.isBuyItNow() ? new BinAuctionViewGUI(box,
                player.getUniqueId(), auctionItem, searcher) : new NormalAuctionViewGUI(box,
                player.getUniqueId(), auctionItem, newCost, searcher);

        player.openInventory(gui.getInventory());
    }
}
