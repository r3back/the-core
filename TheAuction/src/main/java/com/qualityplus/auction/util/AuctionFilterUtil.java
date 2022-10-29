package com.qualityplus.auction.util;

import com.qualityplus.auction.api.box.Box;
import com.qualityplus.auction.base.searcher.AuctionSearcher;
import com.qualityplus.auction.base.searcher.filters.BinFilter;
import com.qualityplus.auction.base.searcher.filters.SortFilter;
import com.qualityplus.auction.base.searcher.filters.StringFilter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.UtilityClass;

@UtilityClass
//@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuctionFilterUtil {
    public AuctionSearcher getSearcher(Box box){
        return AuctionSearcher.builder()
                .stringFilter(new StringFilter(null))
                .sortFilter(SortFilter.HIGHEST_PRICE)
                .binFilter(BinFilter.SHOW_ALL)
                .category(box.files().config().defaultCategory)
                .box(box)
                .build();
    }
}
