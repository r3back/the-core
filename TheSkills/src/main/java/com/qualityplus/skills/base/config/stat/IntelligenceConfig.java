package com.qualityplus.skills.base.config.stat;

import com.qualityplus.assistant.lib.com.cryptomorin.xseries.XMaterial;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import com.qualityplus.skills.base.stat.Stat;
import com.qualityplus.skills.base.stat.stats.IntelligenceStat;
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
 * Utility class for intelligence config
 */
@Getter
@Setter
@Configuration(path = "stats/intelligence_stat.yml")
@Header("================================")
@Header("       Intelligence Stat      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class IntelligenceConfig extends OkaeriConfig implements StatFile {
    private String id = "intelligence";
    private boolean enabled = true;
    private String displayName = "&b✎ Intelligence";
    private List<String> description = Arrays.asList("&7Use your &b%intelligence%✎ Intelligence to" +
            " show", "&7your weapon powers!");
    private int maxLevel = 50;
    private GUIOptions guiOptions = GUIOptions.builder()
            .slot(14)
            .page(1)
            .item(XMaterial.PLAYER_HEAD)
            .texture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQ" +
                    "ubmV0L3RleHR1cmUvYjYyNjUxODc5ZDg3MDQ5OWRhNTBlMzQwMzY4MDBkZGZmZDUyZjNlNGUxOTkzYzVmYzBmYzgyNWQwMzQ0NmQ4YiJ9fX0=")
            .mainMenuLore(Collections.singletonList("%skill_intelligence_description%"))
            .build();

    /**
     * Adds a stat
     *
     * @return {@link Stat}
     */
    public Stat getStat() {
        return IntelligenceStat.builder()
                .id(this.id)
                .displayName(this.displayName)
                .description(this.description)
                .enabled(this.enabled)
                .skillGUIOptions(this.guiOptions)
                .baseAmount(0)
                .build();
    }
}
