package com.qualityplus.auction.base.service;

import com.qualityplus.auction.api.service.AuctionService;
import com.qualityplus.auction.persistence.data.AuctionHouse;
import com.qualityplus.auction.persistence.data.AuctionItem;
import eu.okaeri.platform.core.annotation.Component;
import org.bukkit.Bukkit;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public final class AuctionServiceImpl implements AuctionService {
    private AuctionHouse auctionHouse;

    @Override
    public List<AuctionItem> getItems() {
        return auctionHouse.getNormalItems();
    }

    @Override
    public List<AuctionItem> getItems(UUID owner) {
        return getItems().stream().filter(auctionItem -> auctionItem.getOwner().equals(owner)).collect(Collectors.toList());
    }

    @Override
    public Optional<AuctionHouse> getAuctionHouse() {
        return Optional.ofNullable(auctionHouse);
    }

    @Override
    public void setAuctionHouse(AuctionHouse auctionHouse) {
        this.auctionHouse = auctionHouse;
    }
}
