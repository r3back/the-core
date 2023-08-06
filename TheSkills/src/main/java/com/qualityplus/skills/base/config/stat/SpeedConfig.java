package com.qualityplus.skills.base.config.stat;

import com.qualityplus.assistant.lib.com.cryptomorin.xseries.XMaterial;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import com.qualityplus.skills.base.stat.Stat;
import com.qualityplus.skills.base.stat.stats.SpeedStat;
import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.Header;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.NameModifier;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.NameStrategy;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.Names;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Configuration;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

/**
 * Utility class for speed config
 */
@Getter
@Setter
@Configuration(path = "stats/speed_stat.yml")
@Header("================================")
@Header("       Speed Stat      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class SpeedConfig extends OkaeriConfig implements StatFile {
    private String id = "speed";
    private boolean enabled = true;
    private String displayName = "&f✦ Speed";
    private List<String> description = Collections.singletonList("&7With &a%level_number% &7Speed you have &a%extra_speed%% &7of speed increase.");
    private int maxLevel = 50;
    private GUIOptions guiOptions = GUIOptions.builder()
            .slot(19)
            .page(1)
            .item(XMaterial.PLAYER_HEAD)
            .texture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubm" +
                    "V0L3RleHR1cmUvZjA2NzA2ZWVjYjJkNTU4YWNlMjdhYmRhMGIwYjdiODAxZDM2ZDE3ZGQ3YTg5MGE5NTIwZGJlNTIyMzc0ZjhhNiJ9fX0=")
            .mainMenuLore(Collections.singletonList("%skill_speed_description%"))
            .build();

    /**
     * Adds a stat
     *
     * @return {@link Stat}
     */
    public Stat getStat() {
        return SpeedStat.builder()
                .id(this.id)
                .displayName(this.displayName)
                .description(this.description)
                .enabled(this.enabled)
                .skillGUIOptions(this.guiOptions)
                .extraSpeedPercentagePerLevel(1)
                .baseAmount(0)
                .build();
    }
}
