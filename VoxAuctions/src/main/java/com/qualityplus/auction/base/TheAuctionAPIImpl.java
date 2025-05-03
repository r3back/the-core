package com.qualityplus.auction.base;

import com.qualityplus.assistant.lib.eu.okaeri.injector.annotation.Inject;
import com.qualityplus.auction.api.VoxAuctionsAPI;
import com.qualityplus.auction.api.service.AuctionService;

import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Component;
import lombok.Getter;

/**
 * Utility class for auction api
 */
@Component
public final class TheAuctionAPIImpl implements VoxAuctionsAPI {
    private @Inject
        @Getter AuctionService AuctionService;
}
