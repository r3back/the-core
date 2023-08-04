package com.qualityplus.auction.base.gui.confirm;

import com.qualityplus.assistant.TheAssistantPlugin;
import com.qualityplus.assistant.api.util.BukkitItemUtil;
import com.qualityplus.assistant.api.util.IPlaceholder;
import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.assistant.util.itemstack.ItemStackUtils;
import com.qualityplus.assistant.util.placeholder.Placeholder;
import com.qualityplus.assistant.util.placeholder.PlaceholderBuilder;
import com.qualityplus.auction.api.box.Box;
import com.qualityplus.auction.base.gui.AuctionGUI;
import com.qualityplus.auction.base.gui.all.AllAuctionsGUI;
import com.qualityplus.auction.base.searcher.AuctionSearcher;
import com.qualityplus.auction.persistence.data.AuctionBid;
import com.qualityplus.auction.persistence.data.AuctionItem;
import com.qualityplus.auction.persistence.data.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


/**
 * Utility class for confirm auction
 */
public final class ConfirmAuctionGUI extends AuctionGUI {
    private final ConfirmAuctionGUIConfig config;
    private final AuctionSearcher searcher;
    private final AuctionItem auctionItem;
    private final double auctionPrice;

    /**
     * Adds confirm auction
     *
     * @param boxUtil      {@link Box}
     * @param uuid         {@link UUID}
     * @param searcher     {@link AuctionSearcher}
     * @param auctionItem  {@link AuctionItem}
     * @param auctionPrice if auction price
     */
    public ConfirmAuctionGUI(final Box boxUtil, final UUID uuid, final AuctionSearcher searcher, final AuctionItem auctionItem, final double auctionPrice) {
        super(boxUtil.files().inventories().getConfirmAuctionGUIConfig(), boxUtil);

        this.config = boxUtil.files().inventories().getConfirmAuctionGUIConfig();
        this.auctionPrice = auctionPrice;
        this.auctionItem = auctionItem;
        this.searcher = searcher;
        this.uuid = uuid;
    }

    @Override
    public @NotNull Inventory getInventory() {
        fillInventory(this.config);


        final List<IPlaceholder> placeholders = getAuctionItemPlaceholders(this.auctionItem);

        setItem(this.config.getCloseGUI());
        setItem(this.config.getCancelItem());
        setItem(this.config.getConfirmItem(), PlaceholderBuilder.create(placeholders)
                .with(new Placeholder("auction_to_confirm_price", this.auctionPrice))
                .get());

        inventory.setItem(this.config.getAuctionItem().slot, ItemStackUtils.
                makeItem(this.config.getAuctionItem(), placeholders, this.auctionItem.getItemStack()));

        return inventory;
    }



    @Override
    public void onInventoryClick(final InventoryClickEvent event) {
        event.setCancelled(true);

        final Player player = (Player) event.getWhoClicked();

        final int slot = event.getSlot();

        if (isItem(slot, this.config.getCloseGUI())) {
            player.closeInventory();
        } else if (isItem(slot, this.config.getCancelItem())) {
            player.closeInventory();
        } else if (isItem(slot, this.config.getConfirmItem())) {

            if (this.auctionItem == null) {
                return;
            }

            if (this.auctionItem.isBuyItNow()) {
                if (this.auctionItem.isOwner(uuid)) {
                    player.sendMessage(StringUtils.color(box.files().messages().getAuctionMessages().getYouCannotBidYourOwn()));
                    return;
                }

                if (this.auctionItem.getMarkable().getRemainingTime().isZero() || this.auctionItem.hasBeenBought()) {
                    player.sendMessage(StringUtils.color(box.files().messages().getAuctionMessages().getAuctionExpired()));
                    return;
                }

                if (!canSubmitBid()) {
                    player.sendMessage(StringUtils.color(box.files().messages().getAuctionMessages().getYouCannotAffordIt()));
                    return;
                }

                TheAssistantPlugin.getAPI().getAddons().getEconomy().withdrawMoney(player, this.auctionPrice);

                final List<IPlaceholder> placeholders = Arrays.asList(
                        new Placeholder("auction_owner_name", this.auctionItem.getOwnerName()),
                        new Placeholder("auction_item_name", BukkitItemUtil.getName(this.auctionItem.getItemStack()))
                );

                player.sendMessage(StringUtils.processMulti(box.files().messages().getAuctionMessages().getSuccessfullyBought(), placeholders));

                this.auctionItem.setWhoBought(player.getUniqueId());

                this.auctionItem.addBid(new AuctionBid(player.getUniqueId(), this.auctionPrice, System.currentTimeMillis(), false));

                addToSellerStats();

                player.openInventory(new AllAuctionsGUI(box, 1, player.getUniqueId(), this.searcher).getInventory());

            } else {
                if (this.auctionItem.isOwner(uuid)) {
                    player.sendMessage(StringUtils.color(box.files().messages().getAuctionMessages().getYouCannotBidYourOwn()));
                    return;
                }

                if (this.auctionItem.getMarkable().getRemainingTime().isZero()) {
                    player.sendMessage(StringUtils.color(box.files().messages().getAuctionMessages().getAuctionExpired()));
                    return;
                }

                if (!canSubmitBid()) {
                    player.sendMessage(StringUtils.color(box.files().messages().getAuctionMessages().getYouCannotAffordIt()));
                    return;
                }

                if (isTopBidder()) {
                    player.sendMessage(StringUtils.color(box.files().messages().getAuctionMessages().getYouAlreadyHaveTheHighestBid()));
                    return;
                }

                TheAssistantPlugin.getAPI().getAddons().getEconomy().withdrawMoney(player, this.auctionPrice);

                final List<IPlaceholder> placeholders = Arrays.asList(
                        new Placeholder("bid_amount", this.auctionPrice),
                        new Placeholder("auction_item_name", BukkitItemUtil.getName(this.auctionItem.getItemStack()))
                );

                player.sendMessage(StringUtils.processMulti(box.files().messages().getAuctionMessages().getBidPlaced(), placeholders));

                this.auctionItem.addBid(new AuctionBid(player.getUniqueId(), this.auctionPrice, System.currentTimeMillis(), false));

                addToSellerStats();

                player.openInventory(new AllAuctionsGUI(box, 1, player.getUniqueId(), this.searcher).getInventory());
            }
        }
    }

    private void addToSellerStats() {

        final Optional<User> user = box.getCacheOrDatabase(uuid);

        user.ifPresent(User::addTotalBids);

        user.ifPresent(user1 -> user1.addHighestBid(this.auctionPrice));

    }

    private boolean canSubmitBid() {
        final double money = TheAssistantPlugin.getAPI().getAddons().getEconomy().getMoney(Bukkit.getOfflinePlayer(uuid));

        return money >= auctionPrice;
    }


    private double getTopPrice() {
        return this.auctionItem.getBids().stream()
                .max(Comparator.comparingDouble(AuctionBid::getBidAmount))
                .map(AuctionBid::getBidAmount)
                .orElse(0D);
    }

    private boolean isTopBidder() {
        return uuid.equals(this.auctionItem.getBids().stream()
                .max(Comparator.comparingDouble(AuctionBid::getBidAmount))
                .map(AuctionBid::getBidder)
                .orElse(null));
    }
}
