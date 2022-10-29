package com.qualityplus.auction.base.gui.confirm;

import com.qualityplus.assistant.TheAssistantPlugin;
import com.qualityplus.assistant.api.util.IPlaceholder;
import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.assistant.util.inventory.InventoryUtils;
import com.qualityplus.assistant.util.itemstack.ItemStackUtils;
import com.qualityplus.assistant.util.placeholder.Placeholder;
import com.qualityplus.assistant.util.placeholder.PlaceholderBuilder;
import com.qualityplus.auction.api.box.Box;
import com.qualityplus.auction.base.gui.AuctionGUI;
import com.qualityplus.auction.base.gui.all.AllAuctionsGUI;
import com.qualityplus.auction.base.gui.create.CreateAuctionGUI;
import com.qualityplus.auction.base.gui.main.MainAuctionGUI;
import com.qualityplus.auction.base.gui.view.ViewOpener;
import com.qualityplus.auction.base.searcher.AuctionSearcher;
import com.qualityplus.auction.persistence.data.AuctionBid;
import com.qualityplus.auction.persistence.data.AuctionItem;
import com.qualityplus.auction.persistence.data.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public final class ConfirmAuctionGUI extends AuctionGUI {
    private final ConfirmAuctionGUIConfig config;
    private final AuctionSearcher searcher;
    private final AuctionItem auctionItem;
    private final double auctionPrice;

    public ConfirmAuctionGUI(Box boxUtil, UUID uuid, AuctionSearcher searcher, AuctionItem auctionItem, double auctionPrice) {
        super(boxUtil.files().inventories().confirmAuctionGUIConfig, boxUtil);

        this.config = boxUtil.files().inventories().confirmAuctionGUIConfig;
        this.auctionPrice = auctionPrice;
        this.auctionItem = auctionItem;
        this.searcher = searcher;
        this.uuid = uuid;
    }

    @Override
    public @NotNull Inventory getInventory() {
        fillInventory(config);


        List<IPlaceholder> placeholders = getAuctionItemPlaceholders(auctionItem);

        setItem(config.getCloseGUI());
        setItem(config.cancelItem);
        setItem(config.confirmItem, PlaceholderBuilder.create(placeholders)
                .with(new Placeholder("auction_to_confirm_price", auctionPrice))
                .get());

        inventory.setItem(config.auctionItem.slot, ItemStackUtils.makeItem(config.auctionItem, placeholders, auctionItem.getItemStack()));

        return inventory;
    }



    @Override
    public void onInventoryClick(InventoryClickEvent event) {
        event.setCancelled(true);

        Player player = (Player) event.getWhoClicked();

        int slot = event.getSlot();

        if(isItem(slot, config.getCloseGUI())){
            player.closeInventory();
        }else if(isItem(slot, config.cancelItem)){
            player.closeInventory();
        }else if(isItem(slot, config.confirmItem)){

            if(auctionItem == null) return;

            if(auctionItem.isBuyItNow()){
                if(auctionItem.isOwner(uuid)){
                    player.sendMessage(StringUtils.color(box.files().messages().auctionMessages.youCannotBidYourOwn));
                    return;
                }

                if(auctionItem.getMarkable().getRemainingTime().isZero() || auctionItem.hasBeenBought()){
                    player.sendMessage(StringUtils.color(box.files().messages().auctionMessages.auctionExpired));
                    return;
                }

                if(!canSubmitBid()){
                    player.sendMessage(StringUtils.color(box.files().messages().auctionMessages.youCannotAffordIt));
                    return;
                }

                TheAssistantPlugin.getAPI().getAddons().getEconomy().withdrawMoney(player, auctionPrice);

                List<IPlaceholder> placeholders = Arrays.asList(
                        new Placeholder("auction_owner_name", auctionItem.getOwnerName()),
                        new Placeholder("auction_item_name", ItemStackUtils.getName(auctionItem.getItemStack()))
                );

                player.sendMessage(StringUtils.processMulti(box.files().messages().auctionMessages.successfullyBought, placeholders));

                auctionItem.setWhoBought(player.getUniqueId());

                auctionItem.addBid(new AuctionBid(player.getUniqueId(), auctionPrice, System.currentTimeMillis(), false));

                addToSellerStats();

                player.openInventory(new AllAuctionsGUI(box, 1, player.getUniqueId(), searcher).getInventory());

            }else{
                if(auctionItem.isOwner(uuid)){
                    player.sendMessage(StringUtils.color(box.files().messages().auctionMessages.youCannotBidYourOwn));
                    return;
                }

                if(auctionItem.getMarkable().getRemainingTime().isZero()){
                    player.sendMessage(StringUtils.color(box.files().messages().auctionMessages.auctionExpired));
                    return;
                }

                if(!canSubmitBid()){
                    player.sendMessage(StringUtils.color(box.files().messages().auctionMessages.youCannotAffordIt));
                    return;
                }

                if(isTopBidder()){
                    player.sendMessage(StringUtils.color(box.files().messages().auctionMessages.youAlreadyHaveTheHighestBid));
                    return;
                }

                TheAssistantPlugin.getAPI().getAddons().getEconomy().withdrawMoney(player, auctionPrice);

                List<IPlaceholder> placeholders = Arrays.asList(
                        new Placeholder("bid_amount", auctionPrice),
                        new Placeholder("auction_item_name", ItemStackUtils.getName(auctionItem.getItemStack()))
                );

                player.sendMessage(StringUtils.processMulti(box.files().messages().auctionMessages.bidPlaced, placeholders));

                auctionItem.addBid(new AuctionBid(player.getUniqueId(), auctionPrice, System.currentTimeMillis(), false));

                addToSellerStats();

                player.openInventory(new AllAuctionsGUI(box, 1, player.getUniqueId(), searcher).getInventory());
            }
        }
    }

    private void addToSellerStats(){

        Optional<User> user = box.getCacheOrDatabase(uuid);

        user.ifPresent(User::addTotalBids);

        user.ifPresent(user1 -> user1.addHighestBid(auctionPrice));

    }

    private boolean canSubmitBid(){
        double money = TheAssistantPlugin.getAPI().getAddons().getEconomy().getMoney(Bukkit.getOfflinePlayer(uuid));

        return money >= auctionPrice;
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
}
