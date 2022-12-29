package com.qualityplus.minions.base.minions.minion.layout;

import com.qualityplus.assistant.inventory.Item;
import eu.okaeri.configs.OkaeriConfig;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public final class LayoutItem extends OkaeriConfig {
    private Item item;
    private List<Integer> slots;
}
