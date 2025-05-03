package com.qualityplus.skills.base.config.stat;

import com.qualityplus.assistant.lib.com.cryptomorin.xseries.XMaterial;
import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.Header;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.NameModifier;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.NameStrategy;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.Names;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Configuration;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import com.qualityplus.skills.base.stat.Stat;
import com.qualityplus.skills.base.stat.stats.HealthStat;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@Configuration(path = "stats/extra_health.yml")
@Header("================================")
@Header("       Extra Health Stat      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class MaxHealthConfig extends OkaeriConfig implements StatFile {
    public String id = "extra_health";
    public boolean enabled = true;
    public String displayName = "&c♥ Extra Health";
    public List<String> description = List.of("&a+&c♥%amount%% &7extra health");
    public int maxLevel = 50;
    public GUIOptions guiOptions = GUIOptions.builder()
            .slot(13)
            .page(1)
            .item(XMaterial.PLAYER_HEAD)
            .texture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWEwNmMxY2ZiMDliM2ZkMzMzOTFmZTM4ZjliZDU4Yjg4NDQyNjQxZTM0OTIwNzJhMWZmYWExZjliYmY4MmJhNCJ9fX0=")
            .mainMenuLore(Collections.singletonList("%skill_ferocity_description%"))
            .build();
    public double amountPerLevel = 1;
    public double baseAmount = 0;

    public Stat getStat() {
        return HealthStat.builder()
                .id(id)
                .displayName(displayName)
                .description(description)
                .enabled(enabled)
                .skillGUIOptions(guiOptions)
                .amountPerLevel(amountPerLevel)
                .baseAmount(baseAmount)
                .build();
    }
}
