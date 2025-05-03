package com.qualityplus.skills.api;

import com.qualityplus.skills.api.effect.CommonObject;
import com.qualityplus.skills.api.service.SkillsService;
import com.qualityplus.skills.base.config.Config;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.Map;

public interface VoxSkillsAPI {
    /**
     * Set Perks to an item
     *
     * @param itemStack ItemStack
     * @param perks     Map with perks to add
     * @return Item with perks
     */
    ItemStack setItemAttributes(final ItemStack itemStack, final Map<CommonObject, Double> perks);

    /**
     * Get Stats and perks of an item
     *
     * @param itemStack ItemStack
     * @return Map with stats
     */
    Map<CommonObject, Double> getItemAttributes(final ItemStack itemStack);

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

    /**
     *
     * @return Config instance
     */
    Config getConfig();
}
