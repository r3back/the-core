package com.qualityplus.pets.base.box;

import com.qualityplus.assistant.lib.eu.okaeri.injector.annotation.Inject;
import com.qualityplus.pets.api.box.Box;
import com.qualityplus.pets.api.config.ConfigFiles;
import com.qualityplus.pets.api.service.PetService;
import com.qualityplus.pets.api.service.UserPetService;
import com.qualityplus.pets.base.config.*;

import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Component;
import org.bukkit.plugin.Plugin;

@Component
public final class PetsBox implements Box {
    private @Inject ConfigFiles<Config, Inventories, Messages, Commands, CategoriesFile> files;
    private @Inject UserPetService userPetService;
    private @Inject PetService petService;
    private @Inject Plugin plugin;

    @Override
    public ConfigFiles<Config, Inventories, Messages, Commands, CategoriesFile> files() {
        return files;
    }

    @Override
    public UserPetService service() {
        return userPetService;
    }

    @Override
    public PetService petService() {
        return petService;
    }

    @Override
    public Plugin plugin() {
        return plugin;
    }
}
