package com.qualityplus.skills.persistance.data.user.armor;

import java.util.Map;

public interface LevellableArmorData<T>{
    Map<T, Integer> getFromArmor();

    default void addArmor(T data, Integer quantity) {
        getFromArmor().computeIfPresent(data, (key, aDouble) -> aDouble + quantity);
    }

    default void removeArmor(T data, Integer quantity) {
        getFromArmor().computeIfPresent(data, (key, aDouble) -> aDouble - quantity);
    }

    default Integer getArmor(T data) {
        return getFromArmor().getOrDefault(data, 0);
    }

    default void setArmor(T data, Integer quantity) {
        getFromArmor().computeIfPresent(data, (key, aDouble) -> quantity);
    }
}
