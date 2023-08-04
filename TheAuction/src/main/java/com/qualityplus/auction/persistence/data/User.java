package com.qualityplus.auction.persistence.data;

import com.qualityplus.assistant.lib.eu.okaeri.persistence.document.Document;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data @EqualsAndHashCode(callSuper = false)
public final class User extends Document {
    private String name;
    private UUID uuid;
    private AuctionItem temporalAuction;
    private AuctionStats auctionStats;

    public AuctionStats getAuctionStats(){
        if(auctionStats == null)
            this.auctionStats = new AuctionStats();
        return auctionStats;
    }

    public void addAuctionCompletedWithBids(){
        getAuctionStats().setAuctionsCompletedWithBids(getAuctionStats().getAuctionsCompletedWithBids() + 1);
    }

    public void addAuctionCompletedWithoutBids(){
        getAuctionStats().setAuctionsCompletedWithoutBids(getAuctionStats().getAuctionsCompletedWithoutBids() + 1);
    }

    public void addTotalMoneyEarned(double totalMoneyEarned){
        getAuctionStats().setTotalMoneyEarned(getAuctionStats().getTotalMoneyEarned() + totalMoneyEarned);
    }

    public void addAuctionsWon(){
        getAuctionStats().setAuctionsWon(getAuctionStats().getAuctionsWon() + 1);
    }

    public void addTotalBids(){
        getAuctionStats().setTotalBids(getAuctionStats().getTotalBids() + 1);
    }

    public void addHighestBid(double highestBid){
        if(highestBid > getAuctionStats().getHighestBid()) getAuctionStats().setHighestBid(highestBid);
    }

    public void addMoneySpent(double moneySpent){
        getAuctionStats().setMoneySpent(getAuctionStats().getMoneySpent() + moneySpent);
    }
}
