package com.qualityplus.auction.base.gui.main;

import com.qualityplus.assistant.util.placeholder.Placeholder;
import com.qualityplus.assistant.util.player.PlayerUtils;
import com.qualityplus.auction.api.box.Box;
import com.qualityplus.auction.base.gui.AuctionGUI;
import com.qualityplus.auction.base.gui.all.AllAuctionsGUI;
import com.qualityplus.auction.base.gui.create.CreateAuctionGUI;
import com.qualityplus.auction.base.gui.manage.ManageAuctionGUI;
import com.qualityplus.auction.base.gui.pending.PendingAuctionGUI;
import com.qualityplus.auction.base.gui.stats.AuctionStatsGUI;
import com.qualityplus.auction.base.searcher.AuctionSearcher;
import com.qualityplus.auction.persistence.data.AuctionItem;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public final class MainAuctionGUI extends AuctionGUI {
    private final MainAuctionGUIConfig config;
    private final AuctionSearcher searcher;
    private final int ownAuctions;

    public MainAuctionGUI(Box boxUtil, AuctionSearcher searcher, UUID uuid) {
        super(boxUtil.files().inventories().auctionGUIConfig, boxUtil);

        this.config = boxUtil.files().inventories().auctionGUIConfig;
        this.ownAuctions = getNotClaimedOwned(uuid).size();
        this.searcher = searcher;
        this.uuid = uuid;
    }

    @Override
    public @NotNull Inventory getInventory() {
        fillInventory(config);

        setItem(config.getCloseGUI());

        setItem(config.auctionsBrowser);

        List<AuctionItem> whereBid = getAuctionsWherePlayerBid(uuid);

        if(whereBid.size() <= 0)
            setItem(config.viewBidsEmpty);
        else
            setItem(config.viewBids, Collections.singletonList(new Placeholder("auction_pending_amount", whereBid.size())));

        if(ownAuctions <= 0)
            setItem(config.createAnAuction);
        else
            setItem(config.manageAuctions, Arrays.asList(
                    new Placeholder("player", PlayerUtils.getPlayerName(uuid)),
                    new Placeholder("auction_bids_amount", ownAuctions)
            ));

        setItem(config.auctionStats);

        return inventory;
    }

    @Override
    public void onInventoryClick(InventoryClickEvent event) {
        event.setCancelled(true);

        Player player = (Player) event.getWhoClicked();

        int slot = event.getSlot();

        if(isItem(slot, config.getCloseGUI())){
            player.closeInventory();
        }else if(isItem(slot, config.auctionStats)){
            player.openInventory(new AuctionStatsGUI(box, uuid, searcher).getInventory());
        }else if(isItem(slot, config.auctionsBrowser)){
            player.openInventory(new AllAuctionsGUI(box, 1, uuid, searcher).getInventory());
        }else if(isItem(slot, config.manageAuctions) && ownAuctions > 0){
            player.openInventory(new ManageAuctionGUI(box, uuid, searcher, 1).getInventory());
        }else if(isItem(slot, config.createAnAuction) && ownAuctions <= 0){
            player.openInventory(new CreateAuctionGUI(box, player, searcher).getInventory());
        }else if(isItem(slot, config.viewBids)){
            List<AuctionItem> whereBid = getAuctionsWherePlayerBid(uuid);

            if(whereBid.size() <= 0) return;

            player.openInventory(new PendingAuctionGUI(box, uuid, searcher, 1).getInventory());
        }
    }

}
