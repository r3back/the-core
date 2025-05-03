package com.qualityplus.auction.api.filter.algorithm;

import java.util.List;

/**
 * Adds filter algorithm
 *
 * @param <T> {@link FilterAlgorithm}
 */
public interface FilterAlgorithm<T> {
    /**
     * Add filter
     *
     * @param filter Filter
     * @return Filter
     */
    public List<T> filter(List<T> filter);

    /**
     * Adds a data
     *
     * @param key   Key
     * @param value Value
     */
    public void addData(String key, String value);
}
