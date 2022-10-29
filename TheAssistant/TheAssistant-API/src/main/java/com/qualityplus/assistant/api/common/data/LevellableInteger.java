package com.qualityplus.assistant.api.common.data;


public interface LevellableInteger<T> extends LevellableEmpty<T, Integer> {
    default void addLevel(T data, Integer quantity) {
        getLevel().computeIfPresent(data, (key, aDouble) -> aDouble + quantity);
    }

    default void removeLevel(T data, Integer quantity) {
        getLevel().computeIfPresent(data, (key, aDouble) -> aDouble - quantity);
    }

    default Integer getLevel(T data) {
        return getLevel().getOrDefault(data, 0);
    }
}
