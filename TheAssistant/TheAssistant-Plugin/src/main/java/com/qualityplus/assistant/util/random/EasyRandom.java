package com.qualityplus.assistant.util.random;

import com.qualityplus.assistant.api.util.Randomable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public final class EasyRandom<T> implements Randomable {
    private T item;
    private double probability;
}
