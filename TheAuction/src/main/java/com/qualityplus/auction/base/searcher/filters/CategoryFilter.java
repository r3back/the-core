package com.qualityplus.auction.base.searcher.filters;

import com.qualityplus.auction.api.box.Box;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter
public final class CategoryFilter {
    private final String category;
    private final Box box;
}
