package com.qualityplus.auction.base.searcher.filters;

import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public enum SortFilter {
    RANDOM(3),
    ENDING_SOON(2),
    LOWEST_PRICE(1),
    HIGHEST_PRICE(0);

    public final int level;

    public SortFilter getNext(){
        return Arrays.stream(values())
                .filter(sortBy -> sortBy.level == this.level + 1)
                .findFirst()
                .orElse(SortFilter.HIGHEST_PRICE);
    }
}
