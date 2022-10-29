package com.qualityplus.auction.api.service;

import com.qualityplus.auction.persistence.data.AuctionHouse;
import com.qualityplus.auction.persistence.data.AuctionItem;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AuctionService {
    List<AuctionItem> getItems();

    List<AuctionItem> getItems(UUID owner);

    Optional<AuctionHouse> getAuctionHouse();

    void setAuctionHouse(AuctionHouse auctionHouse);
}
