package com.qualityplus.runes.base.table.effects;

import com.qualityplus.assistant.inventory.Item;
import eu.okaeri.configs.OkaeriConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public final class RuneTableEffect extends OkaeriConfig {
    private Integer slot;
    private Item item;
}
