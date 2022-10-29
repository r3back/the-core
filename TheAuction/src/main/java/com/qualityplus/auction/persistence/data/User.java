package com.qualityplus.auction.persistence.data;

import eu.okaeri.persistence.document.Document;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.UUID;

@Data @EqualsAndHashCode(callSuper = false)
public final class User extends Document {
    private String name;
    private UUID uuid;
    private AuctionItem temporalAuction;
    private AuctionStats auctionStats;

    public void addAuctionCompletedWithBids(){
        auctionStats.setAuctionsCompletedWithBids(auctionStats.getAuctionsCompletedWithBids() + 1);
    }

    public void addAuctionCompletedWithoutBids(){
        auctionStats.setAuctionsCompletedWithoutBids(auctionStats.getAuctionsCompletedWithoutBids() + 1);
    }

    public void addTotalMoneyEarned(double totalMoneyEarned){
        auctionStats.setTotalMoneyEarned(auctionStats.getTotalMoneyEarned() + totalMoneyEarned);
    }

    public void addAuctionsWon(){
        auctionStats.setAuctionsWon(auctionStats.getAuctionsWon() + 1);
    }

    public void addTotalBids(){
        auctionStats.setTotalBids(auctionStats.getTotalBids() + 1);
    }

    public void addHighestBid(double highestBid){
        if(highestBid > auctionStats.getHighestBid()) auctionStats.setHighestBid(highestBid);
    }

    public void addMoneySpent(double moneySpent){
        auctionStats.setMoneySpent(auctionStats.getMoneySpent() + moneySpent);
    }
}
