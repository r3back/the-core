package com.qualityplus.skills.persistance.data.user.armor;

import java.util.Map;

/**
 * Make an progessable armor data
 *
 * @param <T> From armor xp
 */
public interface ProgressableArmorData<T> {
    /**
     * Make an armor xp
     *
     * @return From armor xp
     */
    public Map<T, Double> getFromArmorXp();

    /**
     * Adds an armor xp
     *
     * @param data     Data
     * @param quantity Quantity
     */
    public default void addArmorXP(T data, Double quantity) {
        getFromArmorXp().computeIfPresent(data, (key, aDouble) -> aDouble + quantity);
    }

    /**
     * Adds a remove armor xp
     *
     * @param data     Data
     * @param quantity Quantity
     */
    public default void removeArmorXP(T data, Double quantity) {
        getFromArmorXp().computeIfPresent(data, (key, aDouble) -> aDouble - quantity);
    }

    /**
     * Adds an armor xp
     *
     * @param data Data
     * @return     Armor Xp
     */
    public default Double getArmorXP(T data) {
        return getFromArmorXp().getOrDefault(data, 0D);
    }

    /**
     * Adds an armor xp
     *
     * @param data     Data
     * @param quantity Quantity
     */
    public default void setArmorXP(T data, Double quantity) {
        getFromArmorXp().computeIfPresent(data, (key, aDouble) -> quantity);
    }
}
