package com.qualityplus.auction.api.filter;

import com.qualityplus.auction.api.filter.algorithm.FilterAlgorithm;
import com.qualityplus.auction.api.filter.method.FilterMethod;
import com.qualityplus.auction.persistence.data.AuctionItem;
import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Utility class dor auction filter
 */
@Data
public final class AuctionFilter extends OkaeriConfig implements FilterAlgorithm<AuctionItem> {
    private final Map<String, String> data = new HashMap<>();
    private FilterMethod filterMethod;
    private int priority;
    private String name;


    @Override
    public List<AuctionItem> filter(final List<AuctionItem> filter) {
        return Optional.ofNullable(this.filterMethod.getFilterAlgorithm())
                .map(algorithm -> algorithm.filter(filter))
                .orElse(filter);
    }

    @Override
    public void addData(final String key, final String value) {
        this.data.put(key, value);
    }
}
