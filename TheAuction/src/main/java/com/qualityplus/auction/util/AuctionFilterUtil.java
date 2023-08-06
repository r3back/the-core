package com.qualityplus.auction.util;

import com.qualityplus.auction.api.box.Box;
import com.qualityplus.auction.base.searcher.AuctionSearcher;
import com.qualityplus.auction.base.searcher.filters.BinFilter;
import com.qualityplus.auction.base.searcher.filters.SortFilter;
import com.qualityplus.auction.base.searcher.filters.StringFilter;
import lombok.experimental.UtilityClass;

/**
 *  Utility class for auction filter
 */
@UtilityClass
//@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuctionFilterUtil {
    /**
     * Makes an auction searcher
     *
     * @param box {@link Box}
     * @return an {@link AuctionSearcher}
     */
    public AuctionSearcher getSearcher(final Box box) {
        return AuctionSearcher.builder()
                .stringFilter(new StringFilter(null))
                .sortFilter(SortFilter.HIGHEST_PRICE)
                .binFilter(BinFilter.SHOW_ALL)
                .category(box.files().config().getDefaultCategory())
                .box(box)
                .build();
    }
}
