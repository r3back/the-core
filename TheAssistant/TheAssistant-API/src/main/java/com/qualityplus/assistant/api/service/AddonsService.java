package com.qualityplus.assistant.api.service;
import com.qualityplus.assistant.api.addons.EconomyAddon;
import com.qualityplus.assistant.api.addons.MMOItemsAddon;
import com.qualityplus.assistant.api.addons.MythicMobsAddon;
import com.qualityplus.assistant.api.addons.NPCAddon;
import com.qualityplus.assistant.api.addons.PasterAddon;
import com.qualityplus.assistant.api.addons.PlaceholdersAddon;
import com.qualityplus.assistant.api.addons.RegionAddon;

public interface AddonsService {
    PlaceholdersAddon getPlaceholders();

    MythicMobsAddon getMythicMobs();

    MMOItemsAddon getMmoItems();

    EconomyAddon getEconomy();

    RegionAddon getRegions();

    PasterAddon getPaster();

    NPCAddon getNpc();
}