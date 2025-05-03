package com.qualityplus.auction.base.service;

import com.qualityplus.auction.api.service.AuctionService;
import com.qualityplus.auction.persistence.data.AuctionHouse;
import com.qualityplus.auction.persistence.data.AuctionItem;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Utility class for auction service
 */
@Component
public final class AuctionServiceImpl implements AuctionService {
    private AuctionHouse auctionHouse;

    @Override
    public List<AuctionItem> getItems() {
        return this.auctionHouse.getNormalItems();
    }

    @Override
    public List<AuctionItem> getItems(final UUID owner) {
        return getItems().stream().filter(auctionItem -> auctionItem.getOwner().equals(owner)).collect(Collectors.toList());
    }

    @Override
    public Optional<AuctionHouse> getAuctionHouse() {
        return Optional.ofNullable(this.auctionHouse);
    }

    @Override
    public void setAuctionHouse(final AuctionHouse auctionHouse) {
        this.auctionHouse = auctionHouse;
    }
}
