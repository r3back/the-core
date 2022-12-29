package com.qualityplus.assistant.util.random;

import com.qualityplus.assistant.api.util.Randomable;
import com.qualityplus.assistant.util.math.MathUtils;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

public final class RandomSelector<T extends Randomable> {
    List<T> items;
    Random rand = new Random();
    double totalSum = 0;

    public RandomSelector(List<T> items) {
        this.items = items;

        for(T item : this.items)
            totalSum = totalSum + item.getProbability();
    }

    public T getRandom() {
        if(items == null || items.size() == 1) return null;

        int index = rand.nextInt((int)totalSum);
        double sum = 0;
        int i=0;
        while(sum < index ) {
            sum = sum + items.get(i++).getProbability();
        }
        return items.get(Math.max(0,i-1));
    }

    public T getRandomModified() {
        if(items == null) return null;

        if(items.size() == 1){
            return items.get(0);
        }

        int index = rand.nextInt((int)totalSum);
        double sum = 0;
        int i=0;
        while(sum < index ) {
            sum = sum + items.get(i++).getProbability();
        }
        return items.get(Math.max(0,i-1));
    }

    @Nullable
    public static <T> T getRandom(Map<T, Double> probabilitiesMap){
        List<EasyRandom<T>> items = probabilitiesMap.keySet().stream()
                .map(item -> new EasyRandom<>(item, probabilitiesMap.get(item)))
                .collect(Collectors.toList());

        return Optional.ofNullable(new RandomSelector<>(items).getRandom()).map(EasyRandom::getItem).orElse(null);
    }

    @Nullable
    public static <T> T getRandom(List<T> itemsWithoutProbabilities){
        List<EasyRandom<T>> items = itemsWithoutProbabilities.stream()
                .map(item -> new EasyRandom<>(item, MathUtils.randomBetween(0, 100)))
                .collect(Collectors.toList());

        return Optional.ofNullable(new RandomSelector<>(items).getRandom()).map(EasyRandom::getItem).orElse(null);
    }
}