package com.qualityplus.skills.base.config.stat;

import com.qualityplus.assistant.lib.com.cryptomorin.xseries.XMaterial;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import com.qualityplus.skills.base.stat.Stat;
import com.qualityplus.skills.base.stat.stats.FerocityStat;
import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.Header;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.NameModifier;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.NameStrategy;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.Names;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Configuration;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Utility class for ferocity config
 */
@Getter
@Setter
@Configuration(path = "stats/ferocity_stat.yml")
@Header("================================")
@Header("       Ferocity Stat      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class FerocityConfig extends OkaeriConfig implements StatFile {
    private String id = "ferocity";
    private boolean enabled = true;
    private String displayName = "&c⫽ Ferocity";
    private List<String> description = Arrays.asList("&a%chance%% chance to deal same damage", "&7twice.");
    private int maxLevel = 50;
    private GUIOptions guiOptions = GUIOptions.builder()
            .slot(13)
            .page(1)
            .item(XMaterial.PLAYER_HEAD)
            .texture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0" +
                    "L3RleHR1cmUvZWEwNmMxY2ZiMDliM2ZkMzMzOTFmZTM4ZjliZDU4Yjg4NDQyNjQxZTM0OTIwNzJhMWZmYWExZjliYmY4MmJhNCJ9fX0=")
            .mainMenuLore(Collections.singletonList("%skill_ferocity_description%"))
            .build();

    /**
     * Adds a stat
     *
     * @return {@link FerocityStat}
     */
    public Stat getStat() {
        return FerocityStat.builder()
                .id(this.id)
                .displayName(this.displayName)
                .description(this.description)
                .enabled(this.enabled)
                .skillGUIOptions(this.guiOptions)
                .chancePerLevel(4)
                .baseAmount(0)
                .build();
    }
}
