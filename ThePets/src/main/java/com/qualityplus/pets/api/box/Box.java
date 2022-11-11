package com.qualityplus.pets.api.box;

import com.qualityplus.pets.api.config.ConfigFiles;
import com.qualityplus.pets.api.service.PetService;
import com.qualityplus.pets.api.service.UserPetService;
import com.qualityplus.pets.base.config.*;
import org.bukkit.plugin.Plugin;

/**
 * Box Inteface
 */
public interface Box {
    ConfigFiles<Config, Inventories, Messages, Commands, CategoriesFile> files();
    UserPetService service();
    PetService petService();
    Plugin plugin();
}
