package com.qualityplus.auction.api.filter.algorithm;

import java.util.List;

public interface FilterAlgorithm<T> {
    List<T> filter(List<T> filter);

    void addData(String key, String value);
}
