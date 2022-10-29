package com.qualityplus.auction.base.gui.view.normal;

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
import com.qualityplus.auction.base.gui.view.ViewOpener;
import com.qualityplus.auction.base.searcher.AuctionSearcher;
import com.qualityplus.auction.persistence.data.AuctionBid;
import com.qualityplus.auction.persistence.data.AuctionItem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public final class NormalAuctionViewGUI extends AuctionGUI {
    private final NormalAuctionViewGUIConfig config;
    private final AuctionSearcher searcher;
    private final AuctionItem auctionItem;
    private double newCost = -1;

    public NormalAuctionViewGUI(Box boxUtil, UUID uuid, AuctionItem auctionItem, double newCost, AuctionSearcher searcher) {
        super(boxUtil.files().inventories().submitGUIBuyerConfig, boxUtil);

        this.config = boxUtil.files().inventories().submitGUIBuyerConfig;
        this.auctionItem = auctionItem;
        this.searcher = searcher;
        this.newCost = newCost;
        this.uuid = uuid;
    }


    @Override
    public @NotNull Inventory getInventory() {
        addContent();

        return inventory;
    }

    private List<IPlaceholder> getCollectItemPlaceholders(){
        double ownBid = getPlayerHighest(uuid)
                .map(AuctionBid::getBidAmount)
                .orElse(0D);

        List<IPlaceholder> placeholders = Arrays.asList(new Placeholder("auction_own_bid", ownBid), new Placeholder("auction_top_bid", getTopPrice()));

        String status = StringUtils.processMulti(isTopBidder() ? box.files().messages().auctionMessages.youHadTheTop : box.files().messages().auctionMessages.youHadNotTheTop, placeholders);
        String canCollect = isTopBidder() ? box.files().messages().auctionMessages.youMayCollectItem : box.files().messages().auctionMessages.youMayCollectMoney;
        String auctionCollectItem = isTopBidder() ? box.files().messages().auctionMessages.clickToPickupItem : box.files().messages().auctionMessages.clickToPickupCoins;

        return Arrays.asList(
                new Placeholder("auction_bid_status", status),
                new Placeholder("auction_can_collect_item", canCollect),
                new Placeholder("auction_collect_item_placeholder", auctionCollectItem)
        );
    }

    @Override
    public void addContent() {
        fillInventory(config);

        setItem(config.getCloseGUI());

        List<IPlaceholder> placeholders = getPlaceholders();

        inventory.setItem(config.auctionItem.slot, ItemStackUtils.makeItem(config.auctionItem, PlaceholderBuilder.create(placeholders)
                .with(getBidPlaceholders(auctionItem))
                .get(), auctionItem.getItemStack()));

        if(auctionItem.isExpired()) {
            if(isOwnAuction()){
                if(auctionItem.getWhoBought() == null)
                    setItem(config.collectAuctionEmptyItem, placeholders);
                else
                    setItem(config.collectAuctionItem, placeholders);
            }else
                setItem(config.collectItemAuctionItem, PlaceholderBuilder.create(placeholders)
                        .with(getCollectItemPlaceholders())
                        .get());

        }else
            setItem(config.submitBid, PlaceholderBuilder.create(placeholders)
                    .with(getCollectItemPlaceholders())
                    .get());

        if(!isOwnAuction() && canSubmit() && !auctionItem.isExpired())
            setItem(config.bidAmount, placeholders);



        if(auctionItem.getBids().size() <= 1)
            inventory.setItem(config.bidHistoryEmpty.slot, ItemStackUtils.makeItem(config.bidHistoryEmpty));
        else
            inventory.setItem(config.bidHistoryFilled.slot, ItemStackUtils.makeItem(config.bidHistoryFilled, getPlaceholders()));

        setItem(config.goBack);
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
        }else if((isItem(slot, config.collectAuctionItem) || isItem(slot, config.collectAuctionEmptyItem)) && auctionItem.isExpired() && isOwnAuction()
                && !getPlayerHighest(uuid).map(AuctionBid::isClaimedBack).orElse(false)) {
            //Esto es para agarrar las monedas o el item en caso de que nadie lo haya comprado (seller side)

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
        }else if(isItem(slot, config.collectItemAuctionItem) && !isOwnAuction() && auctionItem.isExpired() && auctionItem.getBid(uuid).isPresent()){
            //Esto es para agarrar las monedas o el item en caso de que lo haya ganado (buyer side)
            player.closeInventory();

            if(isTopBidder()){
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
        }else if(isItem(slot, config.submitBid)){
            if(isOwnAuction()){
                player.sendMessage(StringUtils.color(box.files().messages().auctionMessages.youCannotBidYourOwn));
                return;
            }

            if(auctionItem.getMarkable().getRemainingTime().isZero()){
                player.sendMessage(StringUtils.color(box.files().messages().auctionMessages.auctionExpired));
                return;
            }

            if(!canSubmit()){
                player.sendMessage(StringUtils.color(box.files().messages().auctionMessages.youCannotAffordIt));
                return;
            }

            if(isTopBidder()){
                player.sendMessage(StringUtils.color(box.files().messages().auctionMessages.youAlreadyHaveTheHighestBid));
                return;
            }

            double newTopPrice = Math.max(getAutomaticBidPrice(), newCost);

            player.openInventory(new ConfirmAuctionGUI(box, uuid, searcher, auctionItem, newTopPrice).getInventory());

        }else if(isItem(slot, config.goBack)){
            player.openInventory(new AllAuctionsGUI(box, 1, player.getUniqueId(), searcher).getInventory());
        }else if(isItem(slot, config.bidAmount) && !isOwnAuction() && canSubmit()){
            player.closeInventory();

            SignGUI.builder()
                    .action(event1 -> changeBid(event1.getPlayer(), event1.getLines().get(0)))
                    .withLines(box.files().messages().auctionMessages.submitBid)
                    .uuid(player.getUniqueId())
                    .plugin(box.plugin())
                    .build()
                    .open();
        }
    }

    //Cambiar el bid a uno diferente
    private void changeBid(Player player, String input){
        double newBid;

        if(auctionItem == null) return;

        try {
            newBid = Double.parseDouble(input);

            if(newBid <= 0) throw new NumberFormatException();

            if(newBid < getAutomaticBidPrice()){
                player.sendMessage(StringUtils.color(box.files().messages().auctionMessages.mustBeHigherThanMin.replace("%auction_new_bid%", String.valueOf(getAutomaticBidPrice()))));
                return;
            }

        }catch (NumberFormatException e){
            player.sendMessage(StringUtils.color(box.files().messages().auctionMessages.invalidAmount));
            return;
        }

        ViewOpener.open(player, auctionItem, box, searcher, newBid);
    }

    private List<IPlaceholder> getPlaceholders(){
        String canSubmit = isOwnAuction() ? box.files().messages().auctionMessages.ownAuction :
                isTopBidder() ? box.files().messages().auctionMessages.alreadyTopBid :
                        canSubmit() ? box.files().messages().auctionMessages.canSubmit : box.files().messages().auctionMessages.cannotSubmit;

        return Arrays.asList(
                new Placeholder("auction_owner_name", PlayerUtils.getPlayerName(uuid)),
                new Placeholder("auction_status", getStatusPlaceholder(auctionItem)),
                new Placeholder("auction_item_name", ItemStackUtils.getName(auctionItem.getItemStack())),
                new Placeholder("auction_item_lore", ItemStackUtils.getItemLore(auctionItem.getItemStack())),
                new Placeholder("auction_bids_amount", auctionItem.getBidsWithoutOwner().size()),
                new Placeholder("auction_bid_history", getHistoryPlaceholders(auctionItem)),
                new Placeholder("auction_can_submit", canSubmit),
                new Placeholder("auction_required_bid", getAutomaticBidPrice()),
                new Placeholder("auction_new_bid", Math.max(getAutomaticBidPrice(), newCost)),
                new Placeholder("auction_buyer_name", PlayerUtils.getPlayerName(auctionItem.getWhoBought())),
                new Placeholder("auction_top_bid", getTopPrice()));
    }

    private boolean isOwnAuction(){
        return auctionItem.getOwner().equals(uuid);
    }

    private boolean canSubmit(){
        double money = TheAssistantPlugin.getAPI().getAddons().getEconomy().getMoney(Bukkit.getOfflinePlayer(uuid));

        double toCheckPrice = Math.max(getAutomaticBidPrice(), newCost);

        return money >= toCheckPrice;
    }

    private double getAutomaticBidPrice(){
        return getTopPrice() + ((box.files().config().minPercentageToSubmitNewBid * getTopPrice()) / 100);
    }

    private double getTopPrice(){
        return auctionItem.getBids().stream()
                .max(Comparator.comparingDouble(AuctionBid::getBidAmount))
                .map(AuctionBid::getBidAmount)
                .orElse(0D);
    }

    private boolean isTopBidder(){
        return uuid.equals(auctionItem.getBids().stream()
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
}
