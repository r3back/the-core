package com.qualityplus.skills.persistance.data.user.armor;

import java.util.Map;

public interface ProgressableArmorData<T>{
    Map<T, Double> getFromArmorXp();

    default void addArmorXP(T data, Double quantity) {
        getFromArmorXp().computeIfPresent(data, (key, aDouble) -> aDouble + quantity);
    }

    default void removeArmorXP(T data, Double quantity) {
        getFromArmorXp().computeIfPresent(data, (key, aDouble) -> aDouble - quantity);
    }

    default Double getArmorXP(T data) {
        return getFromArmorXp().getOrDefault(data, 0D);
    }

    default void setArmorXP(T data, Double quantity) {
        getFromArmorXp().computeIfPresent(data, (key, aDouble) -> quantity);
    }
}
