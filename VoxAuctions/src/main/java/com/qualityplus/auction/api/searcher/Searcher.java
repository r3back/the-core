package com.qualityplus.auction.api.searcher;

import java.util.List;

/**
 * Adds an interface searcher
 *
 * @param <T> {@link Searcher}
 */
public interface Searcher<T> {
    /**
     * Adds list
     *
     * @return Filtered
     */
    public List<T> getFiltered();
}
