package com.qualityplus.skills.base.item;

import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.Exclude;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public final class VoxItem extends OkaeriConfig {
    private String itemStackString;
    @Exclude
    private ItemStack itemStack;
    private Map<String, Double> itemPerks;
    private Map<String, Double> itemSkills;
    private int itemUsageDelaySeconds;
    private VoxItemAnimationConfig animationConfig;
    private VoxItemManaConfig manaConfig;
}
