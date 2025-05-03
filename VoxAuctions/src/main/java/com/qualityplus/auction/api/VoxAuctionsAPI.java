package com.qualityplus.auction.api;

import com.qualityplus.auction.api.service.AuctionService;

/**
 * Makes the auction API
 */
public interface VoxAuctionsAPI {
    /**
     * Makes an auctions service
     *
     * @return {@link AuctionService}
     */
    public AuctionService getAuctionService();
}
