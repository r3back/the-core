package com.qualityplus.assistant.util.faster;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
public final class FasterEntry<T, K> implements Map.Entry<T, K>{
    T key;
    K value;

    public static <T, K> FasterEntry<T, K> of(T key, K value){
        return new FasterEntry<>(key, value);
    }

    @SafeVarargs
    public static <T, K> Set<FasterEntry<T, K>> setOf(FasterEntry<T, K>... value){
        return new HashSet<>(Arrays.asList(value));
    }

    @Override
    public K setValue(K value) {
        this.value = value;
        return value;
    }
}