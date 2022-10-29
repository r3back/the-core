package com.qualityplus.auction.base.gui.stats;

import com.qualityplus.assistant.api.util.IPlaceholder;
import com.qualityplus.assistant.util.placeholder.Placeholder;
import com.qualityplus.auction.api.box.Box;
import com.qualityplus.auction.base.gui.AuctionGUI;
import com.qualityplus.auction.base.gui.main.MainAuctionGUI;
import com.qualityplus.auction.base.searcher.AuctionSearcher;
import com.qualityplus.auction.persistence.data.AuctionStats;
import com.qualityplus.auction.persistence.data.User;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public final class AuctionStatsGUI extends AuctionGUI {
    private final AuctionStatsGUIConfig config;
    private final AuctionSearcher searcher;

    public AuctionStatsGUI(Box boxUtil, UUID uuid, AuctionSearcher searcher) {
        super(boxUtil.files().inventories().auctionStatsGUIConfig, boxUtil);

        this.config = boxUtil.files().inventories().auctionStatsGUIConfig;
        this.searcher = searcher;
        this.uuid = uuid;
    }

    @Override
    public @NotNull Inventory getInventory() {
        fillInventory(config);

        List<IPlaceholder> placeholders = getUserPlaceholders();

        setItem(config.buyerStatsItem, placeholders);
        setItem(config.sellerStatsItem, placeholders);

        setItem(config.getCloseGUI());
        setItem(config.goBackItem);

        return inventory;
    }

    private List<IPlaceholder> getUserPlaceholders(){
        AuctionStats stats = box.service().getUser(uuid)
                .map(User::getAuctionStats)
                .orElse(new AuctionStats());

        return Arrays.asList(
                new Placeholder("auctions_data_auctions_created", stats.getAuctionsCreated()),
                new Placeholder("auctions_data_auctions_completed_with_bids", stats.getAuctionsCompletedWithBids()),
                new Placeholder("auctions_data_auctions_completed_without_bids", stats.getAuctionsCompletedWithoutBids()),
                new Placeholder("auctions_data_total_money_earned", stats.getTotalMoneyEarned()),
                new Placeholder("auctions_data_total_money_spent_on_fees", stats.getMoneySpentOnFees()),

                new Placeholder("auctions_data_auctions_won", stats.getAuctionsWon()),
                new Placeholder("auctions_data_total_bids", stats.getTotalBids()),
                new Placeholder("auctions_data_highest_bid", stats.getHighestBid()),
                new Placeholder("auctions_data_total_money_spent", stats.getMoneySpent())
        );
    }

    @Override
    public void onInventoryClick(InventoryClickEvent event) {
        event.setCancelled(true);

        Player player = (Player) event.getWhoClicked();

        int slot = event.getSlot();

        if(isItem(slot, config.getCloseGUI())){
            player.closeInventory();
        }else if(isItem(slot, config.goBackItem)){
            player.openInventory(new MainAuctionGUI(box, searcher, player.getUniqueId()).getInventory());
        }
    }

}
