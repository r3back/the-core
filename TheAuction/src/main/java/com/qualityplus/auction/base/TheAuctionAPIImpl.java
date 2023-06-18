package com.qualityplus.auction.base;

import com.qualityplus.auction.api.TheAuctionAPI;
import com.qualityplus.auction.api.service.AuctionService;

import eu.okaeri.platform.core.annotation.Component;
import lombok.Getter;

@Component
public final class TheAuctionAPIImpl implements TheAuctionAPI {
    private @Inject @Getter AuctionService auctionService;
}
