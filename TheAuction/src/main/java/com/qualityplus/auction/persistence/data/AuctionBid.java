package com.qualityplus.auction.persistence.data;

import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * Utility class for auction bids
 */
@AllArgsConstructor
@Getter
@Setter
public final class AuctionBid extends OkaeriConfig {
    private UUID bidder;
    private double bidAmount;
    private long timeAgo;
    private boolean claimedBack = false;
}
