package com.qualityplus.skills.base.config;

import com.cryptomorin.xseries.XSound;
import com.qualityplus.assistant.api.config.ConfigActionBar;
import com.qualityplus.assistant.api.config.ConfigDatabase;
import com.qualityplus.assistant.api.config.ConfigMessages;
import com.qualityplus.assistant.api.config.ConfigSound;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.*;
import eu.okaeri.platform.core.annotation.Configuration;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Collections;

@Getter
@Setter
@Configuration()
@Header("================================")
@Header("       Config      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class Config extends OkaeriConfig {
    public String prefix = "[Skills] ";

    @Comment("Database Configuration")
    @Comment("Allowed Database Types")
    @Comment("- H2")
    @Comment("- FLAT")
    @Comment("- MYSQL")
    @Comment("- REDIS")
    public ConfigDatabase configDatabase = new ConfigDatabase();

    public GainXPSettings gainXPSettings = new GainXPSettings();

    public LevelUpSettings levelUpSettings = new LevelUpSettings();

    @Getter
    public static class GainXPSettings extends OkaeriConfig{
        public ConfigActionBar actionBar = new ConfigActionBar("&2%skill_displayname% (%skill_xp%/%skill_max_xp%)", true, Collections.emptyList(), Collections.emptyList());
        public ConfigSound sound = new ConfigSound(XSound.ENTITY_EXPERIENCE_ORB_PICKUP, true, 0.2f, 1f);
    }

    @Getter
    public static class LevelUpSettings extends OkaeriConfig{
        public ConfigActionBar actionBar = new ConfigActionBar("&aSuccessfully Level Up &2&l%skill_displayname% %skill_level_roman%", true, Collections.emptyList(), Collections.emptyList());
        public ConfigSound sound = new ConfigSound(XSound.ENTITY_EXPERIENCE_ORB_PICKUP, true, 0.2f, 1f);
        public ConfigMessages message = new ConfigMessages(Arrays.asList(
                "&3&l▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬",
                "&c&b&lSKILL LEVEL UP &3%skill_displayname% %skill_level_roman%",
                "&c  ",
                "&c&a&lREWARDS",
                "",
                "&c%skill_info_message%",
                "&3&l▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬"


                ), true);
    }

    public ConfigActionBar informationActionBar = new ConfigActionBar("&c%player_health%/%player_max_health% ♥       &a%skill_level_defense_number%%skill_defense_displayname%", true, Collections.emptyList(), Collections.emptyList());


}
