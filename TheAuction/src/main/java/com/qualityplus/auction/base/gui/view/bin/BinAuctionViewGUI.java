package com.qualityplus.auction.base.gui.view.bin;

import com.qualityplus.assistant.TheAssistantPlugin;
import com.qualityplus.assistant.api.util.IPlaceholder;
import com.qualityplus.assistant.inventory.SignGUI;
import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.assistant.util.itemstack.ItemStackUtils;
import com.qualityplus.assistant.util.placeholder.Placeholder;
import com.qualityplus.assistant.util.placeholder.PlaceholderBuilder;
import com.qualityplus.assistant.util.player.PlayerUtils;
import com.qualityplus.auction.api.box.Box;
import com.qualityplus.auction.base.gui.AuctionGUI;
import com.qualityplus.auction.base.gui.all.AllAuctionsGUI;
import com.qualityplus.auction.base.gui.confirm.ConfirmAuctionGUI;
import com.qualityplus.auction.base.searcher.AuctionSearcher;
import com.qualityplus.auction.persistence.data.AuctionBid;
import com.qualityplus.auction.persistence.data.AuctionItem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public final class BinAuctionViewGUI extends AuctionGUI {
    private final BinAuctionViewGUIConfig config;
    private final AuctionSearcher searcher;
    private final AuctionItem auctionItem;

    public BinAuctionViewGUI(Box boxUtil, UUID uuid, AuctionItem auctionItem, AuctionSearcher searcher) {
        super(boxUtil.files().inventories().binAuctionViewGUIConfig, boxUtil);

        this.config = boxUtil.files().inventories().binAuctionViewGUIConfig;
        this.auctionItem = auctionItem;
        this.searcher = searcher;
        this.uuid = uuid;
    }

    @Override
    public @NotNull Inventory getInventory() {
        addContent();

        return inventory;
    }

    @Override
    public void addContent() {
        fillInventory(config);

        List<IPlaceholder> placeholders = getPlaceholders();

        inventory.setItem(config.auctionItem.slot, ItemStackUtils.makeItem(config.auctionItem, PlaceholderBuilder.create(placeholders)
                .with(getBidPlaceholders(auctionItem))
                .get(), auctionItem.getItemStack()));

        if(isOwnAuction()){
            if(!auctionItem.isExpired() && auctionItem.getWhoBought() == null){
                setItem(config.cancelAuctionItem, placeholders);
                setItem(config.ownBuyItNowItem, placeholders);
            }else{
                if(auctionItem.getWhoBought() == null)
                    setItem(config.collectAuctionEmptyItem, placeholders);
                else
                    setItem(config.collectAuctionItem, placeholders);
            }
        }else {
            if(auctionItem.getWhoBought() == null && !auctionItem.isExpired())
                setItem(config.buyItNowItem, PlaceholderBuilder.create(placeholders)
                        .get());
            else if(auctionItem.getWhoBought().equals(uuid))
                setItem(config.collectItemAuctionItem, PlaceholderBuilder.create(placeholders)
                        .with(getCollectItemPlaceholders())
                        .get());

        }


        setItem(config.getCloseGUI());
        setItem(config.goBack);
    }

    private List<IPlaceholder> getCollectItemPlaceholders(){
        double ownBid = getPlayerHighest(uuid)
                .map(AuctionBid::getBidAmount)
                .orElse(0D);

        List<IPlaceholder> placeholders = Arrays.asList(new Placeholder("auction_own_bid", ownBid), new Placeholder("auction_top_bid", getTopPrice()));

        String status = StringUtils.processMulti(box.files().messages().auctionMessages.youPaidMinimumPrice, placeholders);
        String canCollect = box.files().messages().auctionMessages.youMayCollectItem;
        String auctionCollectItem = box.files().messages().auctionMessages.clickToPickupItem;

        return Arrays.asList(
                new Placeholder("auction_bid_status", status),
                new Placeholder("auction_can_collect_item", canCollect),
                new Placeholder("auction_collect_item_placeholder", auctionCollectItem)
        );
    }

    private boolean isTopBidder(){
        return uuid.equals(auctionItem.getBids().stream()
                .max(Comparator.comparingDouble(AuctionBid::getBidAmount))
                .map(AuctionBid::getBidder)
                .orElse(null));
    }

    private boolean isTopBinner(){
        return uuid.equals(auctionItem.getBids()
                .stream()
                .filter(bid -> !bid.getBidder().equals(auctionItem.getOwner()))
                .max(Comparator.comparingDouble(AuctionBid::getBidAmount))
                .map(AuctionBid::getBidder)
                .orElse(null));
    }

    protected Optional<AuctionBid> getPlayerHighest(UUID uuid){
        return auctionItem.getBids()
                .stream()
                .max(Comparator.comparingDouble(AuctionBid::getBidAmount))
                .filter(auctionItem -> auctionItem.getBidder().equals(uuid))
                .stream()
                .findFirst();
    }

    private void markAndRemoveIfNeeded(){
        auctionItem.getBids().stream().filter(bid -> bid.getBidder().equals(uuid)).forEach(bid -> bid.setClaimedBack(true));

        int size = (int) auctionItem.getBids().stream().filter(AuctionBid::isClaimedBack).count();

        if(size != auctionItem.getBids().size()) return;

        box.auctionService().getAuctionHouse().ifPresent(auctionHouse -> auctionHouse.getNormalItems().remove(auctionItem));
    }


    @Override
    public void onInventoryClick(InventoryClickEvent event) {
        event.setCancelled(true);

        Player player = (Player) event.getWhoClicked();

        int slot = event.getSlot();

        if(isItem(slot, config.getCloseGUI())) {
            player.closeInventory();
        }else if(isOwnAuction() && isItem(slot, config.ownBuyItNowItem)){
            player.sendMessage(StringUtils.color(box.files().messages().auctionMessages.youCannotBidYourOwn));
        }else if(isItem(slot, config.collectItemAuctionItem) && !isOwnAuction() && auctionItem.getBid(uuid).isPresent()){
            //Esto es para agarrar las monedas o el item en caso de que lo haya ganado (buyer side)
            player.closeInventory();

            if(isTopBinner()){
                player.getInventory().addItem(auctionItem.getItemStack().clone());
                player.sendMessage(StringUtils.color(box.files().messages().auctionMessages.claimedAuctionItem
                        .replace("%auction_owner_name%", auctionItem.getOwnerName())
                        .replace("%auction_item_name%", ItemStackUtils.getName(auctionItem.getItemStack()))
                ));
            }else{
                double ownBid = auctionItem.getBids()
                        .stream().map(AuctionBid::getBidAmount)
                        .max(Comparator.comparingDouble(auctionItem -> auctionItem))
                        .stream().findFirst()
                        .orElse(0D);

                List<IPlaceholder> placeholders = Arrays.asList(
                        new Placeholder("auction_owner_name", auctionItem.getOwnerName()),
                        new Placeholder("auction_own_bid", ownBid));

                player.sendMessage(StringUtils.processMulti(box.files().messages().auctionMessages.claimedMoneyBack, placeholders));

                TheAssistantPlugin.getAPI().getAddons().getEconomy().depositMoney(player, ownBid);
            }

            markAndRemoveIfNeeded();
        }else if(!isOwnAuction() && isItem(slot, config.buyItNowItem)){
            if(isOwnAuction()){
                player.sendMessage(StringUtils.color(box.files().messages().auctionMessages.youCannotBidYourOwn));
                return;
            }

            if(auctionItem.getMarkable().getRemainingTime().isZero() || auctionItem.hasBeenBought()){
                player.sendMessage(StringUtils.color(box.files().messages().auctionMessages.auctionExpired));
                return;
            }

            if(!canSubmit()){
                player.sendMessage(StringUtils.color(box.files().messages().auctionMessages.youCannotAffordIt));
                return;
            }

            double newTopPrice = getTopPrice();

            player.openInventory(new ConfirmAuctionGUI(box, uuid, searcher, auctionItem, newTopPrice).getInventory());

        }else if(isItem(slot, config.goBack)){
            player.openInventory(new AllAuctionsGUI(box, 1, player.getUniqueId(), searcher).getInventory());
        }else if(isOwnAuction() && isItem(slot, config.collectItemAuctionItem)){
            //Esto es para agarrar las monedas o el item en caso de que nadie lo haya comprado (seller side)
            if(auctionItem.getWhoBought() == null && !auctionItem.isExpired()) return;

            player.closeInventory();

            double topPrice = getTopPrice();

            List<IPlaceholder> placeholders = Arrays.asList(
                    new Placeholder("auction_item_name", ItemStackUtils.getName(auctionItem.getItemStack())),
                    new Placeholder("auction_buyer_name", PlayerUtils.getPlayerName(auctionItem.getWhoBought())),
                    new Placeholder("auction_top_bid", topPrice)
            );

            if (auctionItem.getWhoBought() == null) {
                player.sendMessage(StringUtils.processMulti(box.files().messages().auctionMessages.pickUpBack, placeholders));
                player.getInventory().addItem(auctionItem.getItemStack().clone());
            } else {
                player.sendMessage(StringUtils.processMulti(box.files().messages().auctionMessages.collectedMoney, placeholders));
                TheAssistantPlugin.getAPI().getAddons().getEconomy().depositMoney(player, topPrice);
            }

            markAndRemoveIfNeeded();
        }
    }


    private List<IPlaceholder> getPlaceholders(){
        String canSubmit = isOwnAuction() ? box.files().messages().auctionMessages.ownAuction :
                canSubmit() ? box.files().messages().auctionMessages.canSubmitBin : box.files().messages().auctionMessages.cannotSubmit;

        return Arrays.asList(
                new Placeholder("auction_owner_name", PlayerUtils.getPlayerName(uuid)),
                new Placeholder("auction_status", getStatusPlaceholder(auctionItem)),
                new Placeholder("auction_item_name", ItemStackUtils.getName(auctionItem.getItemStack())),
                new Placeholder("auction_item_lore", ItemStackUtils.getItemLore(auctionItem.getItemStack())),
                new Placeholder("auction_bids_amount", auctionItem.getBidsWithoutOwner().size()),
                new Placeholder("auction_bid_history", getHistoryPlaceholders(auctionItem)),
                new Placeholder("auction_buyer_name", PlayerUtils.getPlayerName(auctionItem.getWhoBought())),
                new Placeholder("auction_top_bid", getTopPrice()),
                new Placeholder("auction_can_submit", canSubmit)
        );
    }

    private boolean isOwnAuction(){
        return auctionItem.getOwner().equals(uuid);
    }

    private boolean canSubmit(){

        double money = TheAssistantPlugin.getAPI().getAddons().getEconomy().getMoney(Bukkit.getOfflinePlayer(uuid));

        double toCheckPrice = getTopPrice();

        return money >= toCheckPrice;
    }

    private double getTopPrice(){
        return auctionItem.getBids().stream()
                .max(Comparator.comparingDouble(AuctionBid::getBidAmount))
                .map(AuctionBid::getBidAmount)
                .orElse(0D);
    }
}
