package com.qualityplus.auction.api.searcher;

import java.util.List;

public interface Searcher<T> {
    List<T> getFiltered();
}
