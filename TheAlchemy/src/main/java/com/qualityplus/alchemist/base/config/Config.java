package com.qualityplus.alchemist.base.config;

import com.qualityplus.assistant.api.gui.LoreWrapper;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.*;
import eu.okaeri.platform.core.annotation.Configuration;

@Configuration()
@Header("================================")
@Header("       Config      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class Config extends OkaeriConfig {
    public String prefix = "[TheAlchemist] ";
    @Comment("Mark as true if you want to open plugin's gui")
    @Comment("with the vanilla brewing stand")
    public boolean openAsVanillaBrewingStand = true;
    @Comment("- wrapLength = After how many characters in a lore you want")
    @Comment("               to separate it.")
    @Comment("- wrapStart = After line is separated what character do you")
    @Comment("              want to start the new line with.")
    public LoreWrapper loreWrapper = new LoreWrapper(40, "&7");
}
