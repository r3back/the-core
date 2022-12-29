package com.qualityplus.auction.api.filter.method;

import com.qualityplus.auction.api.filter.algorithm.FilterAlgorithm;
import com.qualityplus.auction.api.filter.type.FilterType;
import com.qualityplus.auction.persistence.data.AuctionItem;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Exclude;
import lombok.Data;

@Data
public final class FilterMethod extends OkaeriConfig {
    private String id;
    private FilterType type;
    @Exclude
    private FilterAlgorithm<AuctionItem> filterAlgorithm;
}
