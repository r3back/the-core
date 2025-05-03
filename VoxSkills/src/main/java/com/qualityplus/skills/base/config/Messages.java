package com.qualityplus.skills.base.config;

import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.Header;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.NameModifier;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.NameStrategy;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.Names;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Configuration;

@Configuration(path = "messages.yml")
@Header("================================")
@Header("       Messages      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class Messages extends OkaeriConfig {
    public PluginMessages pluginMessages = new PluginMessages();
    public SkillsMessages skillsMessages = new SkillsMessages();

    public class SkillsMessages extends OkaeriConfig {
        public String invalidObject = "&cInvalid Object";
        public String statMode = "&aStat Mode";
        public String perkMode = "&aPerk Mode";
        public String skillsIsDisabled = "&cThat skill is disabled!";
        public String addedAmount = "&aYou added &2%amount% &alevels of &2%object% &ato &2%player%&a!, new level is &2%new_level%";
        public String removedAmount = "&aYou removed &2%amount% &alevels of &2%object% &ato &2%player%&a!, new level is &2%new_level%";
        public String successfullyResetStatsAndSkills = "&aYou successfully have reset stats for &2%player%&a!";

    }

    public class PluginMessages extends OkaeriConfig {
        public String successfullyReloaded = "&aPlugin has been reloaded successfully!";

        public String invalidPlayer = "&cInvalid Player!";

        public String useSyntax = "&cUsage: %usage%!";

        public String noPermission = "&eYou don't have permission to do that!";
        public String unknownCommand = "&cUnknown command!";
        public String mustBeAPlayer = "&cYou must be a player to do that!";
        public String invalidAmount = "&cInvalid Amount!";
        public String useHelp = "&cUse: /skills help";

        public String helpMessage = "&7%command% - &e%description%";
        public String helpHeader = "      &6&lVoxSkills   ";
        public String helpfooter = "&e<< &6Page %page% of %maxpage% &e>>";
        public String previousPage = "<<";
        public String nextPage = ">>";
        public String helpPageHoverMessage = "Click to go to page %page%";
    }
}
