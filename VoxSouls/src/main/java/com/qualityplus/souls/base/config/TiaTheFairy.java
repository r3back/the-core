package com.qualityplus.souls.base.config;

import com.qualityplus.assistant.lib.com.cryptomorin.xseries.XSound;
import com.qualityplus.assistant.api.config.ConfigSound;
import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.Header;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.NameModifier;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.NameStrategy;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.Names;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration(path = "tia_the_fairy.yml")
@Header("================================")
@Header("       Tia The Fairy      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class TiaTheFairy extends OkaeriConfig {
    public ConfigSound tiaExchangeSound = new ConfigSound(XSound.ENTITY_FIREWORK_ROCKET_BLAST, true, 0.2f, 1f);

    public int requiredSoulsToExchange = 5;

    public List<String> tiaMessages = Arrays.asList(
            "",
            "  &d&lFAIRY SOUL EXCHANGE",
            "  &fYou gained permanent stat boosts!",
            "",
            "  &8+&610.000 coins"
    );

    public List<String> tiaCommands = Arrays.asList("eco give %player% 10000");
}
