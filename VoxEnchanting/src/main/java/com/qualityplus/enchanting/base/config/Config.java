package com.qualityplus.enchanting.base.config;

import com.qualityplus.assistant.api.gui.LoreWrapper;
import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.Comment;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.Header;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.NameModifier;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.NameStrategy;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.Names;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration()
@Header("================================")
@Header("       Config      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class Config extends OkaeriConfig {
    public String prefix = "[VoxEnchanting] ";
    @Comment("Mark as true if you want to open plugin's gui")
    @Comment("with the vanilla enchantment table")
    public boolean openAsVanillaEnchantmentTable = true;
    @Comment("showEnchantmentIfPlayerDoesntHavePermission - If enchantment should be shown in gui")
    @Comment("if player doesnt have permission")
    public boolean showEnchantmentIfPlayerDoesntHavePermission = true;
    @Comment("- wrapLength = After how many characters in a lore you want")
    @Comment("               to separate it.")
    @Comment("- wrapStart = After line is separated what character do you")
    @Comment("              want to start the new line with.")
    public LoreWrapper loreWrapper = new LoreWrapper(40, "&7");

    public EnchantmentsDisplay enchantmentsDisplay = new EnchantmentsDisplay();

    public class EnchantmentsDisplay extends OkaeriConfig {
        public boolean displayEnchantmentsInLore = true;
        public List<String> enchantmentFormat = Arrays.asList("&9%enchanting_enchant_displayname% %enchanting_enchant_level_roman%", "&7%enchanting_enchant_description%");
        public String enchantmentShrinkFormat = "&9%enchanting_enchant_displayname% %enchanting_enchant_level_roman%";

        public int maxEnchantsInLore = 5;
        public boolean hideVanillaEnchantments = true;

        public int enchantsPerLineIfExceed = 2;

        public String enchantmentSeparator = ", ";

        public LoreLocation loreLocation = LoreLocation.UNDER_ALL;
        public int loreLine = 0;
    }

    public enum LoreLocation{
        ABOVE_ALL,
        UNDER_ALL,
        LINE,
    }
}
