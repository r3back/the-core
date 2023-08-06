package com.qualityplus.skills.base.config.perk;

import com.qualityplus.assistant.lib.com.cryptomorin.xseries.XMaterial;
import com.qualityplus.skills.base.perk.Perk;
import com.qualityplus.skills.base.perk.perks.PotionMasterPerk;
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
 * Utility class for potion master config
 */
@Getter
@Setter
@Configuration(path = "perks/potion_master_perk.yml")
@Header("================================")
@Header("       Potion Master Perk      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class PotionMasterConfig extends OkaeriConfig implements PerkFile {
    private String id = "potion_master";
    private boolean enabled = true;
    private String displayName = "Potion Master";
    private List<String> description = Arrays.asList("  &a%percent%% &7chance to get &a+%duration% &7seconds", "  &7when consume potions.");
    private int maxLevel = 50;
    private GUIOptions guiOptions = GUIOptions.builder()
            .slot(25)
            .page(1)
            .item(XMaterial.PLAYER_HEAD)
            .texture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQub" +
                    "mV0L3RleHR1cmUvNDEyYWMyMzlmMzliYTkxN2U5YmQ2YzE5ZDZlN2RjNDgzMTc5NjUxMDQ3ODdjOGJmY2YwOTBjMGMwMzI3N2FjOSJ9fX0=")
            .mainMenuLore(Collections.singletonList("%skill_iron_lungs_description%"))
            .build();

    /**
     * Adds a perk
     *
     * @return {@link PotionMasterPerk}
     */
    public Perk getPerk() {
        return PotionMasterPerk.builder()
                .id(this.id)
                .displayName(this.displayName)
                .description(this.description)
                .enabled(this.enabled)
                .skillGUIOptions(this.guiOptions)
                .chancePerLevel(0.1)
                .baseSecondsDuration(0)
                .secondsDurationPerLevel(1)
                .level(1)
                .initialAmount(0)
                .build();
    }
}
