package com.qualityplus.skills.base.config.items;

import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;
import com.qualityplus.skills.base.item.VoxItem;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class TestItem extends OkaeriConfig implements CommonVoxItemConfig {
    private VoxItem item;
}
