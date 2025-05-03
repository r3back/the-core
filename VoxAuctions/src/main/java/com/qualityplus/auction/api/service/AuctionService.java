package com.qualityplus.auction.api.service;

import com.qualityplus.auction.persistence.data.AuctionHouse;
import com.qualityplus.auction.persistence.data.AuctionItem;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service interface for auction
 */
public interface AuctionService {
    /**
     * Makes list items
     *
     * @return List Of {@link AuctionItem}
     */
    public List<AuctionItem> getItems();

    /**
     * Adds an auction item
     *
     * @param owner {@link UUID}
     * @return List Of {@link AuctionItem}
     */
    public List<AuctionItem> getItems(final UUID owner);

    /**
     * Adds an auction house
     *
     * @return Optional of {@link AuctionHouse}
     */
    public Optional<AuctionHouse> getAuctionHouse();

    /**
     * Adds an auction house
     *
     * @param auctionHouse {@link AuctionHouse}
     */
    public void setAuctionHouse(final AuctionHouse auctionHouse);
}
