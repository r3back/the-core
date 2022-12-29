package com.qualityplus.skills.api;

import com.qualityplus.skills.api.service.SkillsService;
import com.qualityplus.skills.base.stat.Stat;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.Map;

public interface TheSkillsAPI {
    /**
     * Set Stats to an item
     *
     * @param itemStack ItemStack
     * @param stats     Map with stats to add
     * @return Item with stats
     */
    ItemStack setItemStats(ItemStack itemStack, Map<Stat, Integer> stats);

    /**
     * Get Stats of an item
     *
     * @param itemStack ItemStack
     * @return Map with stats
     */
    Map<Stat, Integer> getItemStats(ItemStack itemStack);

    /**
     * Service to manage Player Skills
     *
     * @return SkillsService
     */
    SkillsService getSkillsService();

    /**
     *
     * @return Plugin Instance
     */
    Plugin getPlugin();
}
