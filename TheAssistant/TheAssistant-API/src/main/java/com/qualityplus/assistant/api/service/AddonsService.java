package com.qualityplus.assistant.api.service;

import com.qualityplus.assistant.api.addons.*;

public interface AddonsService {
    PlaceholdersAddon getPlaceholders();

    MythicMobsAddon getMythicMobs();

    MMOItemsAddon getMmoItems();

    EconomyAddon getEconomy();

    RegionAddon getRegions();

    PasterAddon getPaster();

    NPCAddon getNpc();
}