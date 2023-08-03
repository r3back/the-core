package com.qualityplus.auction.base.gui.pending;

import com.qualityplus.assistant.util.itemstack.ItemStackUtils;
import com.qualityplus.auction.api.box.Box;
import com.qualityplus.auction.base.gui.AuctionGUI;
import com.qualityplus.auction.base.gui.main.MainAuctionGUI;
import com.qualityplus.auction.base.gui.view.ViewOpener;
import com.qualityplus.auction.base.searcher.AuctionSearcher;
import com.qualityplus.auction.persistence.data.AuctionItem;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Utility class for pending auction
 */
public final class PendingAuctionGUI extends AuctionGUI {
    private final Map<Integer, AuctionItem> allAuctions = new HashMap<>();
    private final PendingAuctionGUIConfig config;
    private final AuctionSearcher searcher;

    /**
     * Makes pending auction
     *
     * @param boxUtil  {@link Box}
     * @param uuid     {@link UUID}
     * @param searcher {@link AuctionSearcher}
     * @param page     Page
     */
    public PendingAuctionGUI(final Box boxUtil, final UUID uuid, final AuctionSearcher searcher, final int page) {
        super(boxUtil.files().inventories().getPendingAuctionGUIConfig(), boxUtil);

        this.maxPerPage = box.files().inventories().getPendingAuctionGUIConfig().getAuctionSlots().size();
        this.hasNext = getAuctionsWherePlayerBid(uuid).size() > maxPerPage * page;
        this.config = boxUtil.files().inventories().getPendingAuctionGUIConfig();
        this.searcher = searcher;
        this.page = page;
        this.uuid = uuid;
    }

    @Override
    public @NotNull Inventory getInventory() {
        fillInventory(this.config);

        addContent();

        setItem(this.config.getCloseGUI());
        setItem(this.config.getGoBackItem());

        if (hasNext) {
            setItem(this.config.getNextPageItem());
        }
        if (page > 1) {
            setItem(this.config.getPreviousPageItem());
        }

        return inventory;
    }



    @Override
    public void addContent() {
        final List<AuctionItem> auctions = getAuctionsWherePlayerBid(uuid);

        try {
            int slot = 0;
            int i = maxPerPage * (page - 1);
            if (auctions.size() > 0) {
                while (slot < maxPerPage) {
                    if (auctions.size() > i && i >= 0) {
                        final AuctionItem auctionItem = auctions.get(i);
                        final int finalSlot = this.config.getAuctionSlots().get(slot);
                        inventory.setItem(finalSlot, ItemStackUtils.makeItem(this.config.getAuctionItem(), getAuctionItemPlaceholders(auctionItem),
                                auctionItem.getItemStack()));

                        this.allAuctions.put(finalSlot, auctionItem);
                        slot++;
                        i++;
                        continue;
                    }
                    slot++;
                }
            }
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onInventoryClick(final InventoryClickEvent event) {
        event.setCancelled(true);

        final Player player = (Player) event.getWhoClicked();

        final int slot = event.getSlot();

        if (isItem(slot, this.config.getCloseGUI())) {
            player.closeInventory();
        } else if (isItem(slot, this.config.getGoBackItem())) {
            player.openInventory(new MainAuctionGUI(box, this.searcher, player.getUniqueId()).getInventory());
        } else if (this.allAuctions.containsKey(slot)) {
            final AuctionItem auctionItem = this.allAuctions.get(slot);

            if (auctionItem == null) {
                return;
            }

            ViewOpener.open(player, auctionItem, box, this.searcher, -1);
        } else if (isItem(slot, this.config.getPreviousPageItem()) && page > 1) {
            player.openInventory(new PendingAuctionGUI(box, uuid, this.searcher, page - 1).getInventory());
        } else if (isItem(slot, this.config.getNextPageItem()) && hasNext) {
            player.openInventory(new PendingAuctionGUI(box, uuid, this.searcher, page + 1).getInventory());
        }
    }

}
