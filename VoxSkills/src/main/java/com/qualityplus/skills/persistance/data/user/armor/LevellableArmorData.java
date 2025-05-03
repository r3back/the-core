package com.qualityplus.skills.persistance.data.user.armor;

import java.util.Map;

public interface LevellableArmorData<T>{
    Map<T, Double> getFromArmor();

    default void addArmor(T data, Double quantity) {
        getFromArmor().computeIfPresent(data, (key, aDouble) -> aDouble + quantity);
    }

    default void removeArmor(T data, Double quantity) {
        getFromArmor().computeIfPresent(data, (key, aDouble) -> aDouble - quantity);
    }

    default Double getArmor(T data) {
        return getFromArmor().getOrDefault(data, 0D);
    }

    default void setArmor(T data, Double quantity) {
        getFromArmor().computeIfPresent(data, (key, aDouble) -> quantity);
    }
}
