package com.qualityplus.auction.base.gui.view;

import com.qualityplus.auction.api.box.Box;
import com.qualityplus.auction.base.gui.AuctionGUI;
import com.qualityplus.auction.base.gui.view.bin.BinAuctionViewGUI;
import com.qualityplus.auction.base.gui.view.normal.NormalAuctionViewGUI;
import com.qualityplus.auction.base.searcher.AuctionSearcher;
import com.qualityplus.auction.persistence.data.AuctionItem;
import lombok.experimental.UtilityClass;
import org.bukkit.entity.Player;

@UtilityClass
public class ViewOpener {
    public void open(Player player, AuctionItem auctionItem, Box box, AuctionSearcher searcher, double newCost){
        AuctionGUI gui = auctionItem.isBuyItNow() ? new BinAuctionViewGUI(box, player.getUniqueId(), auctionItem, searcher) : new NormalAuctionViewGUI(box, player.getUniqueId(), auctionItem, newCost, searcher);

        player.openInventory(gui.getInventory());
    }
}
