package com.qualityplus.auction.persistence.data;

import com.qualityplus.assistant.lib.eu.okaeri.persistence.document.Document;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

/**
 * Utility class Users Documents
 */
@Data @EqualsAndHashCode(callSuper = false)
public final class User extends Document {
    private String name;
    private UUID uuid;
    private AuctionItem temporalAuction;
    private AuctionStats auctionStats;

    /**
     * Makes an auction stats
     * @return an {@link AuctionStats}
     */
    public AuctionStats getAuctionStats() {
        if (this.auctionStats == null) {
            this.auctionStats = new AuctionStats();
        }
        return this.auctionStats;
    }

    /**
     * Adds auction completed with bids
     */
    public void addAuctionCompletedWithBids() {
        getAuctionStats().setAuctionsCompletedWithBids(getAuctionStats().getAuctionsCompletedWithBids() + 1);
    }

    /**
     * Adds Auction Completed Without Bids
     */
    public void addAuctionCompletedWithoutBids() {
        getAuctionStats().setAuctionsCompletedWithoutBids(getAuctionStats().getAuctionsCompletedWithoutBids() + 1);
    }

    /**
     *
     * @param totalMoneyEarned TotalMoneyEarned
     */
    public void addTotalMoneyEarned(final double totalMoneyEarned) {
        getAuctionStats().setTotalMoneyEarned(getAuctionStats().getTotalMoneyEarned() + totalMoneyEarned);
    }

    /**
     * Adds a won auctions
     */
    public void addAuctionsWon() {
        getAuctionStats().setAuctionsWon(getAuctionStats().getAuctionsWon() + 1);
    }

    /**
     * Adds a bids totals
     */
    public void addTotalBids() {
        getAuctionStats().setTotalBids(getAuctionStats().getTotalBids() + 1);
    }

    /**
     *
     * @param highestBid HighestBid
     */
    public void addHighestBid(final double highestBid) {
        if (highestBid > getAuctionStats().getHighestBid()) {
            getAuctionStats().setHighestBid(highestBid);
        }
    }

    /**
     * Adds ann money spent
     * @param moneySpent MoneySpent
     */
    public void addMoneySpent(final double moneySpent) {
        getAuctionStats().setMoneySpent(getAuctionStats().getMoneySpent() + moneySpent);
    }
}
