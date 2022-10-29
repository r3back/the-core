package com.qualityplus.assistant.api.common.data;

public interface LevellableDouble<T> extends LevellableEmpty<T, Double> {
    default void addLevel(T data, Double quantity) {
        getLevel().computeIfPresent(data, (key, aDouble) -> aDouble + quantity);
    }

    default void removeLevel(T data, Double quantity) {
        getLevel().computeIfPresent(data, (key, aDouble) -> aDouble - quantity);
    }

    default Double getLevel(T data) {
        return getLevel().getOrDefault(data, 0D);
    }
}
