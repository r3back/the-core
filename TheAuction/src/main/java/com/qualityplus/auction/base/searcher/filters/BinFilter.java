package com.qualityplus.auction.base.searcher.filters;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
public enum BinFilter {
    AUCTION_ONLY(2),
    BIN_ONLY(1),
    SHOW_ALL(0);

    public final int level;

    public BinFilter getNext(){
        return Arrays.stream(values())
                .filter(sortBy -> sortBy.level == this.level + 1)
                .findFirst()
                .orElse(BinFilter.SHOW_ALL);
    }
}
