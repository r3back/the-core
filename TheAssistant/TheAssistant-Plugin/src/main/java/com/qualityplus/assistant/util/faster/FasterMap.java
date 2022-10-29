package com.qualityplus.assistant.util.faster;

import com.google.common.collect.ImmutableMap;

import java.util.List;
import java.util.Map;
import java.util.Set;

public final class FasterMap {
    public static <T, K> Map<T, K> of(Class<T> clazz, Class<K> key, T keyValue, K value){
        return ImmutableMap.<T, K>builder()
                .put(keyValue, value)
                .build();
    }

    public static <T, K> Map<T, K> of(Class<T> clazz, Class<K> key, Set<FasterEntry<T, K>> set){
        return ImmutableMap.<T, K>builder()
                .putAll(set)
                .build();
    }

    public static <T, K> ImmutableMap.Builder<T, K> builder(Class<T> clazz, Class<K> key){
        return ImmutableMap.<T, K>builder();
    }

    public static <T, K> ImmutableMap.Builder<T, List<K>> listBuilder(Class<T> clazz, Class<K> key){
        return ImmutableMap.<T, List<K>>builder();
    }

    public static <T, K> Map<T, K> empty(){
        return ImmutableMap.<T, K>builder().build();
    }
}
