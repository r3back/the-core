package com.qualityplus.pets.base.config;

import com.qualityplus.assistant.api.gui.LoreWrapper;
import com.qualityplus.assistant.api.config.ConfigDatabase;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.*;
import eu.okaeri.platform.core.annotation.Configuration;

@Configuration()
@Header("================================")
@Header("       Config      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class Config extends OkaeriConfig {
    public String prefix = "[ThePets] ";

    @Comment("Database Configuration")
    @Comment("Allowed Database Types")
    @Comment("- H2")
    @Comment("- FLAT")
    @Comment("- MYSQL")
    @Comment("- REDIS")
    public ConfigDatabase configDatabase = new ConfigDatabase();

    @Comment("- wrapLength = After how many characters in a lore you want")
    @Comment("               to separate it.")
    @Comment("- wrapStart = After line is separated what character do you")
    @Comment("              want to start the new line with.")
    public LoreWrapper loreWrapper = new LoreWrapper(40, "&7");
}
