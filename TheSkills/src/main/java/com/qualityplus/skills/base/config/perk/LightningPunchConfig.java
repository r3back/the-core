package com.qualityplus.skills.base.config.perk;

import com.qualityplus.assistant.lib.com.cryptomorin.xseries.XMaterial;
import com.qualityplus.skills.base.perk.Perk;
import com.qualityplus.skills.base.perk.perks.LightningPunchPerk;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
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
 * Utility class for lightning punch
 */
@Getter
@Setter
@Configuration(path = "perks/lightning_punch_perk.yml")
@Header("================================")
@Header("       Lightning Punch Perk      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class LightningPunchConfig extends OkaeriConfig implements PerkFile {
    private String id = "lightning_punch";
    private boolean enabled = true;
    private String displayName = "Lightning Punch";
    private List<String> description = Arrays.asList("  &a%percent%% &7chance to strike a lightning",
            "  &7that makes &a%damage% &7damage while", "  &7fighting.");
    private int maxLevel = 50;
    private GUIOptions guiOptions = GUIOptions.builder()
            .slot(19)
            .page(1)
            .item(XMaterial.PLAYER_HEAD)
            .texture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3Jh" +
                    "ZnQubmV0L3RleHR1cmUvNzg1YzE5YmVhMWUwYWExYjNmOGY5ODUzYzc1NGI1MzQ2NzcxOGNlMGY5MGRlODg2ZmQ0ZWYyYjQyMDk5NTVjNSJ9fX0=")
            .mainMenuLore(Collections.singletonList("%skill_lightning_punch_description%"))
            .build();

    /**
     * Adds a perk
     *
     * @return {@link LightningPunchPerk}
     */
    public Perk getPerk() {
        return LightningPunchPerk.builder()
                .id(this.id)
                .displayName(this.displayName)
                .description(this.description)
                .enabled(this.enabled)
                .skillGUIOptions(this.guiOptions)
                .chancePerLevel(0.1)
                .initialAmount(0)
                .damage(5)
                .build();
    }
}
