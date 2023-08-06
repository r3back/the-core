package com.qualityplus.auction.base.searcher.filters;

import lombok.AllArgsConstructor;

import java.util.Arrays;

/**
 * makes a bin filters
 */
@AllArgsConstructor
public enum BinFilter {
    AUCTION_ONLY(2),
    BIN_ONLY(1),
    SHOW_ALL(0);

    public final int level;

    /**
     * Makes a bin filter
     *
     * @return {@link BinFilter}
     */
    public BinFilter getNext() {
        return Arrays.stream(values())
                .filter(sortBy -> sortBy.level == this.level + 1)
                .findFirst()
                .orElse(BinFilter.SHOW_ALL);
    }
}
