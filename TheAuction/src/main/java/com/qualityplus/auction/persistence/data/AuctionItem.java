package com.qualityplus.auction.persistence.data;

import com.qualityplus.assistant.util.time.Markable;
import eu.okaeri.configs.OkaeriConfig;
import lombok.*;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter @Setter
public final class AuctionItem extends OkaeriConfig {

    private UUID owner;
    private String ownerName;
    private ItemStack itemStack;
    private Markable markable;
    private String timerId;
    private List<AuctionBid> bids;
    private boolean isBuyItNow;
    private UUID whoBought;
    private Boolean hasBids;

    @Builder
    public AuctionItem(UUID owner, String ownerName, ItemStack itemStack, Markable markable, String timer, List<AuctionBid> bids, boolean isBuyItNow) {
        this.owner = owner;
        this.ownerName = ownerName;
        this.itemStack = itemStack;
        this.markable = markable;
        this.timerId = timer;
        this.bids = bids;
        this.isBuyItNow = isBuyItNow;
    }

    public boolean hasBeenBought(){
        return whoBought != null;
    }

    public ItemStack getItemStack() {
        return Optional.ofNullable(itemStack)
                .map(ItemStack::clone)
                .orElse(null);
    }

    public Optional<AuctionBid> getBid(UUID uuid){
        return bids.stream().filter(bid -> bid.getBidder().equals(uuid)).findFirst();
    }

    public List<AuctionBid> getBidsWithoutOwner(){
        return bids.stream().filter(bid -> !bid.getBidder().equals(owner)).collect(Collectors.toList());
    }

    public boolean isExpired(){
        return markable.getRemainingTime().isZero();
    }


    public void addBid(AuctionBid bid){
        bids.add(bid);
    }

    public double getHighestBid(){
        return new ArrayList<>(bids).stream()
                .min((o1, o2) -> (int) (o1.getBidAmount() - o2.getBidAmount()))
                .map(AuctionBid::getBidAmount)
                .orElse(0D);
    }

    public double getHighestBid(UUID uuid){
        return new ArrayList<>(bids).stream()
                .min((o1, o2) -> (int) (o1.getBidAmount() - o2.getBidAmount()))
                .filter(bid -> bid.getBidder().equals(uuid))
                .map(AuctionBid::getBidAmount)
                .orElse(0D);
    }

    public Set<UUID> getBidders(){
        return bids.stream()
                .map(AuctionBid::getBidder)
                .filter(bidder -> !bidder.equals(owner))
                .collect(Collectors.toSet());
    }

    public boolean isOwner(UUID uuid){
        return owner.equals(uuid);
    }

}
