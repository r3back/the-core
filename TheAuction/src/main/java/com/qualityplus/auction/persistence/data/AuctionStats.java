package com.qualityplus.auction.persistence.data;

import eu.okaeri.configs.OkaeriConfig;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data @EqualsAndHashCode(callSuper = true)
public final class AuctionStats extends OkaeriConfig {
    //Buyer
    private int auctionsCreated;  //Done
    private int auctionsCompletedWithBids; //Done
    private int auctionsCompletedWithoutBids; //Done
    private double totalMoneyEarned; //Done
    private double moneySpentOnFees; //Done

    //Seller
    private int auctionsWon;
    private double totalBids;
    private double highestBid;
    private double moneySpent;
}
