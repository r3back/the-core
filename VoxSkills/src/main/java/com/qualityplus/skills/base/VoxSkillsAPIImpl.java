package com.qualityplus.skills.base;

import com.qualityplus.assistant.api.util.BukkitItemUtil;
import com.qualityplus.assistant.lib.de.tr7zw.changeme.nbtapi.NBTItem;
import com.qualityplus.assistant.lib.eu.okaeri.injector.annotation.Inject;
import com.qualityplus.skills.api.VoxSkillsAPI;
import com.qualityplus.skills.api.effect.CommonObject;
import com.qualityplus.skills.api.service.SkillsService;
import com.qualityplus.skills.base.config.Config;
import com.qualityplus.skills.base.perk.registry.Perks;
import com.qualityplus.skills.base.stat.Stat;
import com.qualityplus.skills.base.stat.registry.Stats;

import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Component;
import lombok.Getter;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public final class VoxSkillsAPIImpl implements VoxSkillsAPI {
    private static final String NBT_PERK_KEY = "SKILL_PERK_";
    private static final String NBT_STAT_KEY = "SKILL_STAT_";
    private @Getter @Inject SkillsService skillsService;
    private @Getter @Inject Plugin plugin;
    private @Getter @Inject Config config;

    @Override
    public ItemStack setItemAttributes(ItemStack itemStack, Map<CommonObject, Double> attributes) {
        if (BukkitItemUtil.isNull(itemStack)) {
            return itemStack;
        }

        final NBTItem item = new NBTItem(itemStack);

        attributes.forEach((key, value) -> {
            if (key instanceof Stat) {
                item.setDouble(NBT_STAT_KEY + key.getId(), value);
            } else {
                item.setDouble(NBT_PERK_KEY + key.getId(), value);
            }
        });

        return item.getItem();
    }

    @Override
    public Map<CommonObject, Double> getItemAttributes(final ItemStack itemStack) {
        if (BukkitItemUtil.isNull(itemStack)) {
            return new HashMap<>();
        }

        final NBTItem item = new NBTItem(itemStack);

        final Map<CommonObject, Double> stats = item.getKeys().stream()
                .filter(key -> key.contains(NBT_STAT_KEY))
                .map(key -> Stats.getByID(key.replace(NBT_STAT_KEY, "")))
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(stat -> stat, stat -> item.getDouble(NBT_STAT_KEY + stat.getId())));

        final Map<CommonObject, Double> perks = item.getKeys().stream()
                .filter(key -> key.contains(NBT_PERK_KEY))
                .map(key -> Perks.getByID(key.replace(NBT_PERK_KEY, "")))
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(perk -> perk, perk -> item.getDouble(NBT_PERK_KEY + perk.getId())));

        stats.putAll(perks);

        return stats;
    }
}
