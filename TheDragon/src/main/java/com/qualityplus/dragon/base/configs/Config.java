package com.qualityplus.dragon.base.configs;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.assistant.api.config.ConfigDatabase;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.util.itemstack.ItemBuilder;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.*;
import eu.okaeri.platform.core.annotation.Configuration;

import java.util.Arrays;

@Configuration()
@Header("================================")
@Header("       Config      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class Config extends OkaeriConfig {
    public String prefix = "&d&lTheDragon &f»";

    @CustomKey("configDatabase")
    @Comment("Database Configuration")
    @Comment("Allowed Database Types")
    @Comment("- H2")
    @Comment("- FLAT")
    @Comment("- MYSQL")
    @Comment("- REDIS")
    public ConfigDatabase configDatabase = new ConfigDatabase();

    public GameSettings eventSettings = new GameSettings();
    public Item enderKeyItem = ItemBuilder.of(XMaterial.PLAYER_HEAD, 1, "&aEpic Ender Eye", Arrays.asList("&eLeft-Click at an altar to place.", "", "&lEPIC"))
            .headData("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGFhOGZjOGRlNjQxN2I0OGQ0OGM4MGI0NDNjZjUzMjZlM2Q5ZGE0ZGJlOWIyNWZjZDQ5NTQ5ZDk2MTY4ZmMwIn19fQ==").build();


    public static class GameSettings extends OkaeriConfig{
        public DragonSettings dragonSettings = new DragonSettings();
        public GeneralSettings generalSettings = new GeneralSettings();
        public SchematicSettings schematicSettings = new SchematicSettings();
    }

    public static class GeneralSettings extends OkaeriConfig{
        public boolean invincibleCrystals = true;
        public int dragonSpawnCountdownSeconds = 5;
    }

    public static class SchematicSettings extends OkaeriConfig{
        public String id = "default_structure";
    }


    public static class DragonSettings extends OkaeriConfig{
        public boolean takeFromMythicMobs = false;
        public boolean useRandomDragon = true;
        public String dragonId = "ancient_dragon";
        public int mythicMobDragonLevel = 1;
    }
}
