package com.qualityplus.alchemist.base.config;

import com.qualityplus.assistant.api.gui.LoreWrapper;
import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;

import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.Comment;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.Header;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.NameModifier;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.NameStrategy;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.Names;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Configuration;
import lombok.Getter;
import lombok.Setter;

/**
 * Config file representation
 */
@Getter
@Setter
@Configuration()
@Header("================================")
@Header("       Config      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class Config extends OkaeriConfig {
    private String prefix = "[TheAlchemist] ";
    @Comment("Mark as true if you want to open plugin's gui")
    @Comment("with the vanilla brewing stand")
    private boolean openAsVanillaBrewingStand = true;
    @Comment("- wrapLength = After how many characters in a lore you want")
    @Comment("               to separate it.")
    @Comment("- wrapStart = After line is separated what character do you")
    @Comment("              want to start the new line with.")
    private LoreWrapper loreWrapper = new LoreWrapper(40, "&7");
}
