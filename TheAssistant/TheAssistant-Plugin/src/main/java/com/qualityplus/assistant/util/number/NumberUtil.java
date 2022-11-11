package com.qualityplus.assistant.util.number;

import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@UtilityClass
public class NumberUtil {
    public List<Integer> intStream(int from, int to){
        return IntStream.range(from, to).boxed().collect(Collectors.toList());
    }
}
