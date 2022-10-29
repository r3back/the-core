package com.qualityplus.auction.base.gui.manage;

import com.qualityplus.assistant.util.itemstack.ItemStackUtils;
import com.qualityplus.auction.api.box.Box;
import com.qualityplus.auction.base.gui.AuctionGUI;
import com.qualityplus.auction.base.gui.all.AllAuctionsGUI;
import com.qualityplus.auction.base.gui.create.CreateAuctionGUI;
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

public final class ManageAuctionGUI extends AuctionGUI {
    private final Map<Integer, AuctionItem> allAuctions = new HashMap<>();
    private final ManageAuctionGUIConfig config;
    private final AuctionSearcher searcher;

    public ManageAuctionGUI(Box boxUtil, UUID uuid, AuctionSearcher searcher, int page) {
        super(boxUtil.files().inventories().manageAuctionGUIConfig, boxUtil);

        this.maxPerPage = box.files().inventories().manageAuctionGUIConfig.auctionSlots.size();
        this.hasNext = getNotClaimedOwned(uuid).size() > maxPerPage * page;
        this.config = boxUtil.files().inventories().manageAuctionGUIConfig;
        this.searcher = searcher;
        this.page = page;
        this.uuid = uuid;
    }

    @Override
    public @NotNull Inventory getInventory() {
        fillInventory(config);

        addContent();

        setItem(config.getCloseGUI());
        setItem(config.goBackItem);
        setItem(config.sortItem);
        setItem(config.createAnAuction);

        if(hasNext)
            setItem(config.nextPageItem);

        if(page > 1)
            setItem(config.previousPageItem);


        return inventory;
    }



    @Override
    public void addContent() {
        List<AuctionItem> auctions = getNotClaimedOwned(uuid);

        try {
            int slot = 0;
            int i = maxPerPage * (page - 1);
            if(auctions.size() > 0){
                while (slot < maxPerPage) {
                    if (auctions.size() > i && i >= 0) {
                        AuctionItem auctionItem = auctions.get(i);
                        int finalSlot = config.auctionSlots.get(slot);
                        inventory.setItem(finalSlot, ItemStackUtils.makeItem(config.auctionItem, getAuctionItemPlaceholders(auctionItem), auctionItem.getItemStack()));

                        allAuctions.put(finalSlot, auctionItem);
                        slot++;
                        i++;
                        continue;
                    }
                    slot++;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
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
        }else if(isItem(slot, config.createAnAuction)){
            player.openInventory(new CreateAuctionGUI(box, player, searcher).getInventory());
        }else if(allAuctions.containsKey(slot)){
            AuctionItem auctionItem = allAuctions.get(slot);

            if(auctionItem == null) return;

            ViewOpener.open(player, auctionItem, box, searcher, -1);
        }else if(isItem(slot, config.previousPageItem) && page > 1){
            player.openInventory(new ManageAuctionGUI(box, uuid, searcher, page - 1).getInventory());
        }else if(isItem(slot, config.nextPageItem) && hasNext){
            player.openInventory(new ManageAuctionGUI(box, uuid, searcher, page + 1).getInventory());
        }
    }

}
