package com.qualityplus.minions.base.minions.minion.layout;

import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public final class LayoutGUISettings extends OkaeriConfig {
    private List<LayoutItem> layoutItems;
    private int minionSlot;
}
