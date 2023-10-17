package com.qualityplus.skills.base.config.perk;

import com.qualityplus.assistant.lib.com.cryptomorin.xseries.XMaterial;
import com.qualityplus.skills.base.perk.Perk;
import com.qualityplus.skills.base.perk.perks.AbilityDamagePerk;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
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
 * Utility class for ability damage config
 */
@Getter
@Setter
@Configuration(path = "perks/ability_damage_perk.yml")
@Header("================================")
@Header("       Ability Damage Perk      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class AbilityDamageConfig extends OkaeriConfig implements PerkFile {
    private String id = "ability_damage";
    private boolean enabled = true;
    private String displayName = "Ability Damage";
    private List<String> description = Collections.singletonList("&aSoon...");
    private int maxLevel = 50;
    private GUIOptions guiOptions = GUIOptions.builder()
            .slot(19)
            .item(XMaterial.PLAYER_HEAD)
            .texture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1" +
                    "cmUvNzQ5MmNhOTQwNzkxMzZkMjUyNTcwM2QzNzVjMjU1N2VhYzIwMWVlN2RkMzljZTExYzY0YTljMzgxNDdlY2M0ZCJ9fX0=")
            .mainMenuLore(Collections.singletonList("%skill_ability_damage_description%"))
            .build();

    /**
     * Adds a perk
     *
     * @return {@link AbilityDamagePerk}
     */
    public Perk getPerk() {
        return AbilityDamagePerk.builder()
                .id(this.id)
                .displayName(this.displayName)
                .description(this.description)
                .enabled(this.enabled)
                .skillGUIOptions(this.guiOptions)
                .chancePerLevel(0.1)
                .initialAmount(0)
                .build();
    }
}
