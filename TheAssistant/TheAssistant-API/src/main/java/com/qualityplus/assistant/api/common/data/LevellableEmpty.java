package com.qualityplus.assistant.api.common.data;

import java.util.Map;

public interface LevellableEmpty<T, N> {
    Map<T, N> getLevel();

    void addLevel(T data, N quantity);

    void removeLevel(T data, N quantity);

    N getLevel(T data);

    default void setLevel(T data, N quantity){
        getLevel().put(data, quantity);
    }
}
