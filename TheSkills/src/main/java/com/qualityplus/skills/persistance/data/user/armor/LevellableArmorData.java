package com.qualityplus.skills.persistance.data.user.armor;

import java.util.Map;

/**
 * Make a levellable Armor data
 * @param <T> From armor
 */
public interface LevellableArmorData<T> {
    /**
     * Adds a from armor
     *
     * @return From Armor
     */
    public Map<T, Integer> getFromArmor();

    /**
     * Adds a armor
     *
     * @param data     Data
     * @param quantity Quantity
     */
    public default void addArmor(T data, Integer quantity) {
        getFromArmor().computeIfPresent(data, (key, aDouble) -> aDouble + quantity);
    }

    /**
     * Make a remove armor
     *
     * @param data     Data
     * @param quantity Quantity
     */
    public default void removeArmor(T data, Integer quantity) {
        getFromArmor().computeIfPresent(data, (key, aDouble) -> aDouble - quantity);
    }

    /**
     * Adds a armor
     *
     * @param data Data
     * @return Armor
     */
    public default Integer getArmor(T data) {
        return getFromArmor().getOrDefault(data, 0);
    }

    /**
     * Adds a armor
     *
     * @param data     Data
     * @param quantity Quantity
     */
    public default void setArmor(T data, Integer quantity) {
        getFromArmor().computeIfPresent(data, (key, aDouble) -> quantity);
    }
}
