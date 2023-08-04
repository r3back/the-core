package com.qualityplus.auction.base.tasks;

import com.qualityplus.assistant.lib.eu.okaeri.injector.annotation.Inject;
import com.qualityplus.auction.api.box.Box;
import com.qualityplus.auction.persistence.data.AuctionBid;
import com.qualityplus.auction.persistence.data.AuctionItem;
import com.qualityplus.auction.persistence.data.User;
import com.qualityplus.assistant.lib.eu.okaeri.commons.bukkit.time.MinecraftTimeEquivalent;;

import com.qualityplus.assistant.lib.eu.okaeri.platform.bukkit.annotation.Scheduled;

import java.util.Comparator;
import java.util.Optional;
import java.util.UUID;

/**
 * Utility class for expiration task
 */
@Scheduled(rate = MinecraftTimeEquivalent.SECOND, async = true)
public final class AuctionExpirationTask implements Runnable {
    private @Inject Box box;

    @Override
    public void run() {
        for (AuctionItem auctionItem : this.box.auctionService().getItems()) {
            if (auctionItem.isBuyItNow()) {
                continue;
            }

            if (!auctionItem.isExpired()) {
                continue;
            }

            final UUID topBidder = getTopPrice(auctionItem);

            final UUID owner = auctionItem.getOwner();

            if (auctionItem.getHasBids() != null) {
                continue;
            }

            final Optional<User> user = this.box.getCacheOrDatabase(owner);

            if (owner.equals(topBidder)) {
                auctionItem.setHasBids(false);
                user.ifPresent(User::addAuctionCompletedWithoutBids);
            } else {
                auctionItem.setWhoBought(topBidder);
                auctionItem.setHasBids(true);
                //Add a bid to total completed and money earned from this auction
                user.ifPresent(User::addAuctionCompletedWithBids);
                user.ifPresent(user1 -> user1.addTotalMoneyEarned(auctionItem.getHighestBid()));
                //Add the user who won 1 auction item
                this.box.getCacheOrDatabase(topBidder).ifPresent(User::addAuctionsWon);
                //Add the max money spent to all users
                auctionItem.getBidders().forEach(bidder -> this.box.getCacheOrDatabase(bidder).
                        ifPresent(user1 -> user1.addMoneySpent(auctionItem.getHighestBid(bidder))));
            }
        }
    }

    private UUID getTopPrice(final AuctionItem auctionItem) {
        return auctionItem.getBids().stream()
                .max(Comparator.comparingDouble(AuctionBid::getBidAmount))
                .map(AuctionBid::getBidder)
                .orElse(null);
    }
}
