package com.qualityplus.alchemist.base.stand;

import com.qualityplus.assistant.inventory.Item;
import eu.okaeri.configs.OkaeriConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public final class StandEffect extends OkaeriConfig {
    private Integer slot;
    private Item item;
}
