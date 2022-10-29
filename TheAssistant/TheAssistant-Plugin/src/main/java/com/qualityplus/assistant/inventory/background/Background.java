package com.qualityplus.assistant.inventory.background;

import com.qualityplus.assistant.inventory.Item;
import eu.okaeri.configs.OkaeriConfig;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public final class Background extends OkaeriConfig {
    public Map<Integer, Item> items;
    public Item filler;
    public boolean useFiller;

    /**
     * Default Constructor
     *
     * @param items BackGround Slots and Items
     */
    public Background(Map<Integer, Item> items) {
        this.items = items;
    }
}
