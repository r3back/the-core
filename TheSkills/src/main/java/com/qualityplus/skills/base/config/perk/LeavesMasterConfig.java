package com.qualityplus.skills.base.config.perk;

import com.qualityplus.assistant.lib.com.cryptomorin.xseries.XMaterial;
import com.google.common.collect.ImmutableMap;
import com.qualityplus.skills.base.perk.Perk;
import com.qualityplus.skills.base.perk.perks.LeavesMasterPerk;
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
 * Utility class for leaves master config
 */
@Getter
@Setter
@Configuration(path = "perks/leaves_master_perk.yml")
@Header("================================")
@Header("       Leaves Master Perk      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class LeavesMasterConfig extends OkaeriConfig implements PerkFile {
    private String id = "leaves_master";
    private boolean enabled = true;
    private String displayName = "Leaves Master";
    private List<String> description = Arrays.asList("  &a%percent%% &7chance to get get random", "  &7drops while cutting leaves.");
    private int maxLevel = 50;
    private GUIOptions guiOptions = GUIOptions.builder()
            .slot(16)
            .page(1)
            .item(XMaterial.PLAYER_HEAD)
            .texture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1c" +
                    "mUvZGU3Y2Q2MWY1MzgzZjNhOTgxN2U3MmY1YThkM2FiMzQ2NDY2MWU2ZWY0YzEwNGI5MTQwMjU3NDA4ZGI1YTM4YSJ9fX0=")
            .mainMenuLore(Collections.singletonList("%skill_leaves_master_description%"))
            .build();

    /**
     * Adds a perk
     *
     * @return {@link LeavesMasterPerk}
     */
    public Perk getPerk() {
        return LeavesMasterPerk.builder()
                .id(this.id)
                .displayName(this.displayName)
                .description(this.description)
                .enabled(this.enabled)
                .skillGUIOptions(this.guiOptions)
                .chancePerLevel(0.1)
                .initialAmount(0)
                .itemAndChances(ImmutableMap.<XMaterial, Double>builder()
                        .put(XMaterial.WHEAT, 8D)
                        .put(XMaterial.DIAMOND, 1D)
                        .put(XMaterial.GOLD_INGOT, 3D)
                        .build())
                .build();
    }
}
