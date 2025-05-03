package com.qualityplus.skills.base.config.perk;

import com.qualityplus.assistant.lib.com.cryptomorin.xseries.XMaterial;
import com.qualityplus.skills.base.perk.Perk;
import com.qualityplus.skills.base.perk.perks.OrbMasterPerk;
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

@Getter
@Setter
@Configuration(path = "perks/orb_master_perk.yml")
@Header("================================")
@Header("       Orb Master Perk      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class OrbMasterConfig extends OkaeriConfig implements PerkFile {
    public String id = "orb_master";
    public boolean enabled = true;
    public String displayName = "Orb Master";
    public List<String> description = Arrays.asList("  &a%percent%% &7chance to get double", "  &7experience orbs.");
    public int maxLevel = 50;
    public GUIOptions guiOptions = GUIOptions.builder()
            .slot(24)
            .page(1)
            .item(XMaterial.PLAYER_HEAD)
            .texture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWMzYTRiNTFhNDgwMDI4OTBmYjI3MWNkYzE1MTdlYWY0MWFiMDAyNTNjMWI3ZDgyNDI3MjczNmJiNDE2NjNkNSJ9fX0=")
            .mainMenuLore(Collections.singletonList("%skill_orb_master_description%"))
            .build();
    public double chancePerLevel = 0.1;
    public int initialAmount = 0;

    public Perk getPerk() {
        return OrbMasterPerk.builder()
                .id(id)
                .displayName(displayName)
                .description(description)
                .enabled(enabled)
                .skillGUIOptions(guiOptions)
                .chancePerLevel(chancePerLevel)
                .initialAmount(initialAmount)
                .build();
    }
}
