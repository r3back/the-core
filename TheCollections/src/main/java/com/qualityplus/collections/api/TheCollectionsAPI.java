package com.qualityplus.collections.api;

import com.qualityplus.collections.api.service.CollectionsService;
import org.bukkit.plugin.Plugin;

public interface TheCollectionsAPI {
    /**
     * Service to manage Player Skills
     *
     * @return SkillsService
     */
    CollectionsService getCollectionsService();

    /**
     *
     * @return Plugin Instance
     */
    Plugin getPlugin();
}
