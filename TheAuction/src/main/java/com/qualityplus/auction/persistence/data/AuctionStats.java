package com.qualityplus.auction.persistence.data;

import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Makes auction stats
 */
@Data @EqualsAndHashCode(callSuper = true)
public final class AuctionStats extends OkaeriConfig {
    //Buyer
    private int auctionsCreated;
    private int auctionsCompletedWithBids;
    private int auctionsCompletedWithoutBids;
    private double totalMoneyEarned;
    private double moneySpentOnFees;

    //Seller
    private int auctionsWon;
    private double totalBids;
    private double highestBid;
    private double moneySpent;
}
