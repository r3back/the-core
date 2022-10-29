package com.qualityplus.assistant.api.common.data;

import java.util.Map;

public interface ProgressableData<T>{
    Map<T, Double> getXp();

    default void addXp(T data, Double quantity) {
        getXp().computeIfPresent(data, (key, aDouble) -> aDouble + quantity);
    }

    default void removeXp(T data, Double quantity) {
        getXp().computeIfPresent(data, (key, aDouble) -> aDouble - quantity);
    }

    default Double getXp(T data) {
        return getXp().getOrDefault(data, 0D);
    }

    default void setXp(T data, Double quantity) {
        getXp().computeIfPresent(data, (key, aDouble) -> quantity);
    }
}
